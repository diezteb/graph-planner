import net.gexf.format.graph.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.ztis.planner.service.ServicePublisher;
import pl.edu.agh.ztis.planner.ws.GraphPlanningPortType;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;
import pl.edu.agh.ztis.planner.ws.PlanningTask;
import pl.edu.agh.ztis.planner.ws.PlanningTaskResponse;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bootstrap.xml")
public class ServiceTest {

    @Autowired
    private ServicePublisher servicePublisher;

    @Autowired
    @Value("${service.url}")
    private String url;

    @Before
    public void setup() {
        servicePublisher.run();
    }

    @Test
    public void shouldWork() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(GraphPlanningPortType.class);
        factory.setAddress(url);
        GraphPlanningPortType client = (GraphPlanningPortType) factory.create();

        PlanningTaskResponse planningTaskResponse = client.schedulePlanning(new PlanningTask()
                .withAlgorithm(PlanningAlgorithm.DIJKSTRA)
                .withGraph(new GexfContent()
                        .withGraph(new GraphContent()
                                .withAttributesOrNodesOrEdges(new NodesContent()
                                        .withNode(new NodeContent().withId("1"))
                                        .withNode(new NodeContent().withId("2"))
                                ).withAttributesOrNodesOrEdges(new EdgesContent()
                                        .withEdge(new EdgeContent()
                                                .withSource("1")
                                                .withTarget("2")
                                        )
                                ).withStart("1")
                                .withEnd("2")
                        )
                )
        );
        assertEquals("Planning task scheduled for execution", planningTaskResponse.getStatus());
    }

}
