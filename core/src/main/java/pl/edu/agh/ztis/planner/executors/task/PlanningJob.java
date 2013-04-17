package pl.edu.agh.ztis.planner.executors.task;

import java.util.concurrent.Callable;

import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.planners.Planner;

public class PlanningJob implements Callable<PlanningResult> {

    private Planner planner;
    private PlanningProblem problem;

    public PlanningJob(Planner planner, PlanningProblem problem) {
        this.planner = planner;
        this.problem = problem;
    }

    @Override
    public PlanningResult call() {
        return planner.executePlanning(problem);
    }

}
