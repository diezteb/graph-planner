package pl.edu.agh.ztis.client;

import net.gexf.format.graph.Gexf;
import net.gexf.format.graph.Graph;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.ws.*;

@Component
public class ServiceInvoker {
    private static final String URL = "http://localhost:9000/planning";
    private static final String RESPONSE_SERVICE_URL = "http://localhost:8080/results";

    @Autowired
    private ResultsHolder resultsHolder;

    public PlanningTaskResponse invoke(Graph graph) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(GraphPlanningPortType.class);
        factory.setAddress(URL);
        GraphPlanningPortType client = (GraphPlanningPortType) factory.create();

        PlanningTask planningTask = new PlanningTask()
                .withAlgorithm(PlanningAlgorithm.DIJKSTRA)
                .withGraph(new Gexf()
                        .withGraph(graph)
                )
                .withResponseService(new ResponseService()
                        .withMethod(ResponseMethod.POST)
                        .withUrl(RESPONSE_SERVICE_URL)
                );
        PlanningTaskResponse planningTaskResponse = client.schedulePlanning(planningTask);
        resultsHolder.storePlanningTask(planningTaskResponse.getJobId(), planningTask);
        return planningTaskResponse;
    }

}
