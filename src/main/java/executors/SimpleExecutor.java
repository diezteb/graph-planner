package executors;

import executors.task.PlanningTask;
import measures.Measure;
import model.ExecutionResult;
import model.PlanningResult;

public class SimpleExecutor {

    public ExecutionResult execute(PlanningTask task, Measure measure) {
        ExecutionResult results = new ExecutionResult();
        measure.initialize();
        PlanningResult planningResult = task.call();
        measure.finalize(planningResult);
        results.setResult(planningResult);
        results.setStatistics(measure.getStatistics());
        return results;
    }
}
