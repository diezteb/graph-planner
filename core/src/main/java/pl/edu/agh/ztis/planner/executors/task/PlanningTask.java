package pl.edu.agh.ztis.planner.executors.task;

import java.util.concurrent.Callable;

import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.planners.Planner;

public class PlanningTask implements Callable<PlanningResult> {

    private Planner planner;
    private PlanningProblem problem;

    @Override
    public PlanningResult call() {
        return planner.executePlanning(problem);
    }

    /**
     * @return the planner
     */
    public Planner getPlanner() {
        return planner;
    }

    /**
     * @param planner
     *            the planner to set
     */
    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    /**
     * @return the problem
     */
    public PlanningProblem getProblem() {
        return problem;
    }

    /**
     * @param problem
     *            the problem to set
     */
    public void setProblem(PlanningProblem problem) {
        this.problem = problem;
    }

}
