package pl.edu.agh.ztis.planner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.ws.GraphPlanningPortType;
import pl.edu.agh.ztis.planner.ws.PlanningTask;
import pl.edu.agh.ztis.planner.ws.PlanningTaskResponse;

import javax.jws.WebParam;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static java.lang.String.format;

@Component
public class PlanningServiceImpl implements GraphPlanningPortType {

//    @Autowired
//    private ModelMapper mapper;
//
//    @Autowired
//    private AsynchronousExecutor executor;

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanningServiceImpl.class);

    private String tempDirectory;

    public PlanningServiceImpl() {
        try {
            this.tempDirectory = Files.createTempDirectory("jobs").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlanningTaskResponse schedulePlanning(
            @WebParam(partName = "parameters", name = "planning-task", targetNamespace = "http://agh.edu.pl/ztis/planning") PlanningTask parameters) {
        String jobId = UUID.randomUUID().toString();
        String jobPath = storeJob(jobId, parameters);

//        PlanningJob planningJob = mapper.mapPlanningTask(parameters);
//        planningJob.setJobId(jobId);
//
//        Measure measure = getAllMeasures();
//        String status = executor.execute(planningJob, measure);

        try {
            String[] args = new String[]{"mvn", "exec:java", format("-Dexec.args=%s", jobPath)};
            Process process = Runtime.getRuntime().exec(args, null, Paths.get("executor").toFile());
            logJobExecution(process.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PlanningTaskResponse()/*.withStatus(status)*/.withJobId(jobId);
    }

    private String storeJob(String jobId, PlanningTask planningTask) {
        try {
            File file = Files.createFile(Paths.get(tempDirectory, jobId)).toFile();
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(planningTask);
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobId;
    }

    private void logJobExecution(InputStream stream) {
        if (LOGGER.isDebugEnabled()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println("line = " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private Measure getAllMeasures() {
//        AggregateMeasure measure = new AggregateMeasure();
//        measure.addMeasure(new ExecutionTimeMeasure());
//        measure.addMeasure(new MemoryMeasure());
//        measure.addMeasure(new PathLengthMeasure());
//        return measure;
//    }
}
