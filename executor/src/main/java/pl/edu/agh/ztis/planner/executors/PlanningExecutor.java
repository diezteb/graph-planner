package pl.edu.agh.ztis.planner.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.mappers.ModelMapper;
import pl.edu.agh.ztis.planner.measures.*;
import pl.edu.agh.ztis.planner.ws.PlanningTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;

@Component
public class PlanningExecutor {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AsynchronousExecutor executor;

    public void executePlanning(String jobPath) throws IOException, ClassNotFoundException {
        File file = Paths.get(jobPath).toFile();
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
        PlanningTask planningTask = (PlanningTask) stream.readObject();

        String[] parts = jobPath.split("/");
        String jobId = parts[parts.length - 1];
        PlanningJob<?> planningJob = mapper.mapPlanningTask(planningTask);
        planningJob.setJobId(jobId);

        Measure measure = getAllMeasures();
        executor.execute(planningJob, measure);
    }


    private Measure getAllMeasures() {
        AggregateMeasure measure = new AggregateMeasure();
        measure.addMeasure(new ExecutionTimeMeasure());
        measure.addMeasure(new MemoryMeasure());
        measure.addMeasure(new PathLengthMeasure());
        return measure;
    }
}
