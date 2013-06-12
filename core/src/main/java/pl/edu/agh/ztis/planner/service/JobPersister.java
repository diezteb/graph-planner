package pl.edu.agh.ztis.planner.service;

import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.ws.PlanningTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class JobPersister {
    private String tempDirectory;

    public JobPersister() {
        try {
            this.tempDirectory = Files.createTempDirectory("jobs").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String storeJob(String jobId, PlanningTask planningTask) throws IOException {
        File file = Files.createFile(Paths.get(tempDirectory, jobId)).toFile();
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(planningTask);
        return file.getAbsolutePath();
    }

}
