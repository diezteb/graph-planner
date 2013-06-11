package pl.edu.agh.ztis.planner.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.measures.Measure;
import pl.edu.agh.ztis.planner.model.ExecutionResult;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.ws.ResponseService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AsynchronousExecutor {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private RestTemplate restTemplate;

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

                ResponseService responseService = task.getResponseService();

                switch (responseService.getMethod()) {
                    case POST:
                        HttpEntity<ExecutionResult> entity = new HttpEntity<>(results);
                        restTemplate.postForEntity(responseService.getUrl(), entity, Void.class);
                        break;
                }
            }
        });

        return "Planning task scheduled for execution";
    }

}
