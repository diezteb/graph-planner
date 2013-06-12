package pl.edu.agh.ztis.planner.executors.task;

import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.ws.ResponseService;

import java.util.concurrent.Callable;

public class PlanningJob<T> implements Callable<PlanningResult> {

    private Planner<T> planner;
    private PlanningProblem<? extends T> problem;
    private ResponseService responseService;
    private String jobId;

    public PlanningJob(Planner<T> planner, PlanningProblem<? extends T> problem, ResponseService responseService) {
        this.planner = planner;
        this.problem = problem;
        this.responseService = responseService;
    }

    @Override
    public PlanningResult call() {
        return planner.executePlanning(problem);
    }

    public ResponseService getResponseService() {
        return responseService;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
