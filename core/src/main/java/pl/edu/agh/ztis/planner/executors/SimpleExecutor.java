package pl.edu.agh.ztis.planner.executors;

import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.measures.Measure;
import pl.edu.agh.ztis.planner.model.ExecutionResult;
import pl.edu.agh.ztis.planner.model.PlanningResult;

public class SimpleExecutor {

    public ExecutionResult execute(PlanningJob task, Measure measure) {
        ExecutionResult results = new ExecutionResult();
        measure.initialize();
        PlanningResult planningResult = task.call();
        measure.finalize(planningResult);
        results.setResult(planningResult);
        results.setStatistics(measure.getStatistics());
        return results;
    }
}
