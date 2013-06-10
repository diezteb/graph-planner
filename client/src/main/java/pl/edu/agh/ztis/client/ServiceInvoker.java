package pl.edu.agh.ztis.client;

import net.gexf.format.graph.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.ws.GraphPlanningPortType;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;
import pl.edu.agh.ztis.planner.ws.PlanningTask;
import pl.edu.agh.ztis.planner.ws.PlanningTaskResponse;

@Component
public class ServiceInvoker {

    public static final String URL = "http://localhost:9000/planning";

    public PlanningTaskResponse invoke(GraphContent graph) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(GraphPlanningPortType.class);
        factory.setAddress(URL);
        GraphPlanningPortType client = (GraphPlanningPortType) factory.create();

        PlanningTaskResponse planningTaskResponse = client.schedulePlanning(new PlanningTask()
                .withAlgorithm(PlanningAlgorithm.DIJKSTRA)
                .withGraph(new GexfContent()
                        .withGraph(graph)
                )
        );
        return planningTaskResponse;
    }

}
