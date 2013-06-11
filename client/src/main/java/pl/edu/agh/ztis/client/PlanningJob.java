package pl.edu.agh.ztis.client;

import pl.edu.agh.ztis.planner.model.ExecutionResult;
import pl.edu.agh.ztis.planner.ws.PlanningTask;

public class PlanningJob {
    private PlanningTask task;
    private ExecutionResult result;

    PlanningJob(PlanningTask task) {
        this.task = task;
    }

    public void setResult(ExecutionResult result) {
        this.result = result;
    }

    public PlanningTask getTask() {
        return task;
    }

    public ExecutionResult getResult() {
        return result;
    }
}
