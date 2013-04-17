package pl.edu.agh.ztis.planner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.executors.AsynchronousExecutor;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.mappers.ModelMapper;
import pl.edu.agh.ztis.planner.measures.ExecutionTimeMeasure;
import pl.edu.agh.ztis.planner.ws.GraphPlanningPortType;
import pl.edu.agh.ztis.planner.ws.PlanningTask;
import pl.edu.agh.ztis.planner.ws.PlanningTaskResponse;

import javax.jws.WebParam;

@Component
public class PlanningServiceImpl implements GraphPlanningPortType {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AsynchronousExecutor executor;

    @Override
    public PlanningTaskResponse schedulePlanning(
            @WebParam(partName = "parameters", name = "planning-task", targetNamespace = "http://agh.edu.pl/ztis/planning") PlanningTask parameters) {
        PlanningJob planningJob = mapper.mapPlanningTask(parameters);

        String status = executor.execute(planningJob, new ExecutionTimeMeasure());

        return new PlanningTaskResponse().withStatus(status);
    }
}
