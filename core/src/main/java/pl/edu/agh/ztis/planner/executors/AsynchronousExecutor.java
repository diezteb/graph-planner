package pl.edu.agh.ztis.planner.executors;

import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.measures.Measure;
import pl.edu.agh.ztis.planner.model.ExecutionResult;
import pl.edu.agh.ztis.planner.model.PlanningResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AsynchronousExecutor {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public String execute(final PlanningJob task, final Measure measure) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ExecutionResult results = new ExecutionResult();
                measure.initialize();
                PlanningResult planningResult = task.call();
                measure.finalize(planningResult);
                results.setResult(planningResult);
                results.setStatistics(measure.getStatistics());


                // TODO send result to specified server
            }
        });

        return "Planning task scheduled for execution";
    }

}
