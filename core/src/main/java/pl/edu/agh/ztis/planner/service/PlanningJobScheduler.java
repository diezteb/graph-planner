package pl.edu.agh.ztis.planner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.ws.PlanningTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

@Component
public class PlanningJobScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanningServiceImpl.class);
    private static final String EXECUTOR_MODULE = "executor";
    private ExecutorService loggingService;

    @Autowired
    private JobPersister jobPersister;

    public PlanningJobScheduler() {
        if (LOGGER.isInfoEnabled()) {
            loggingService = Executors.newFixedThreadPool(10);
        }
    }

    public void scheduleForExecution(String jobId, PlanningTask parameters) {
        try {
            String jobPath = jobPersister.storeJob(jobId, parameters);
            String[] args = createCmd(jobPath);
            Process process = Runtime.getRuntime().exec(args, null, Paths.get(EXECUTOR_MODULE).toFile());
            logJobExecution(process.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] createCmd(String jobPath) {
        return new String[]{"mvn", "exec:java", format("-Dexec.args=%s", jobPath)};
    }

    private void logJobExecution(final InputStream stream) {
        if (LOGGER.isInfoEnabled()) {
            loggingService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            LOGGER.info(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
