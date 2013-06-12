package pl.edu.agh.ztis.planner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.ws.GraphPlanningPortType;
import pl.edu.agh.ztis.planner.ws.PlanningTask;
import pl.edu.agh.ztis.planner.ws.PlanningTaskResponse;

import javax.jws.WebParam;
import java.util.UUID;

@Component
public class PlanningServiceImpl implements GraphPlanningPortType {

    private static final String STATUS = "Planning task scheduled for execution";

    @Autowired
    private PlanningJobScheduler scheduler;

    @Override
    public PlanningTaskResponse schedulePlanning(
            @WebParam(partName = "parameters", name = "planning-task", targetNamespace = "http://agh.edu.pl/ztis/planning") PlanningTask parameters) {

        String jobId = UUID.randomUUID().toString();
        scheduler.scheduleForExecution(jobId, parameters);
        return new PlanningTaskResponse().withStatus(STATUS).withJobId(jobId);
    }

}
