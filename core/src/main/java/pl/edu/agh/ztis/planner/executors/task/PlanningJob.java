package pl.edu.agh.ztis.planner.executors.task;

import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.planners.Planner;

import java.util.concurrent.Callable;

public class PlanningJob<T> implements Callable<PlanningResult> {

    private Planner<T> planner;
    private PlanningProblem<? extends T> problem;

    public PlanningJob(Planner<T> planner, PlanningProblem<? extends T> problem) {
        this.planner = planner;
        this.problem = problem;
    }

    @Override
    public PlanningResult call() {
        return planner.executePlanning(problem);
    }

}
