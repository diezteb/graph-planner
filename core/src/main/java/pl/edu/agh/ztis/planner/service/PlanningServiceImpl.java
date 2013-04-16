package pl.edu.agh.ztis.planner.service;

import pl.edu.agh.ztis.planning.PlanningService;
import pl.edu.agh.ztis.planning.PlanningTask;
import pl.edu.agh.ztis.planning.PlanningTaskResponse;

import javax.jws.WebParam;

public class PlanningServiceImpl implements PlanningService {
    @Override
    public PlanningTaskResponse schedulePlanning(@WebParam(partName = "parameters", name = "planning-task", targetNamespace = "http://agh.edu.pl/ztis/planning") PlanningTask parameters) {
        PlanningTaskResponse planningTaskResponse = new PlanningTaskResponse();
        planningTaskResponse.setReturn("yeah");
        return planningTaskResponse;
    }
}
