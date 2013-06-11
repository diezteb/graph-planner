package pl.edu.agh.ztis.client;

import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.model.ExecutionResult;
import pl.edu.agh.ztis.planner.ws.PlanningTask;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ResultsHolder {

    private final ConcurrentMap<String, PlanningJob> resultsMap = new ConcurrentHashMap<>();

    public void storePlanningTask(String jobId, PlanningTask task) {
        resultsMap.put(jobId, new PlanningJob(task));
    }

    public void storeResult(ExecutionResult result){
        resultsMap.get(result.getJobId()).setResult(result);
    }

    private static class PlanningJob{
        private PlanningTask task;
        private ExecutionResult result;

        private PlanningJob(PlanningTask task) {
            this.task = task;
        }

        private void setResult(ExecutionResult result) {
            this.result = result;
        }
    }
}
