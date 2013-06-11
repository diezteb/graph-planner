package pl.edu.agh.ztis.planner.service;

import javax.jws.WebParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.agh.ztis.planner.executors.AsynchronousExecutor;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.mappers.ModelMapper;
import pl.edu.agh.ztis.planner.measures.AggregateMeasure;
import pl.edu.agh.ztis.planner.measures.ExecutionTimeMeasure;
import pl.edu.agh.ztis.planner.measures.Measure;
import pl.edu.agh.ztis.planner.measures.MemoryMeasure;
import pl.edu.agh.ztis.planner.measures.PathLengthMeasure;
import pl.edu.agh.ztis.planner.ws.GraphPlanningPortType;
import pl.edu.agh.ztis.planner.ws.PlanningTask;
import pl.edu.agh.ztis.planner.ws.PlanningTaskResponse;

import java.util.UUID;

@Component
public class PlanningServiceImpl implements GraphPlanningPortType {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AsynchronousExecutor executor;

    @Override
    public PlanningTaskResponse schedulePlanning(
            @WebParam(partName = "parameters", name = "planning-task", targetNamespace = "http://agh.edu.pl/ztis/planning") PlanningTask parameters) {
        String jobId = UUID.randomUUID().toString();
        PlanningJob planningJob = mapper.mapPlanningTask(parameters);
        planningJob.setJobId(jobId);

        Measure measure = getAllMeasures();
        String status = executor.execute(planningJob, measure);

        return new PlanningTaskResponse().withStatus(status).withJobId(jobId);
    }

    private Measure getAllMeasures() {
        AggregateMeasure measure = new AggregateMeasure();
        measure.addMeasure(new ExecutionTimeMeasure());
        measure.addMeasure(new MemoryMeasure());
        measure.addMeasure(new PathLengthMeasure());
        return measure;
    }
}
