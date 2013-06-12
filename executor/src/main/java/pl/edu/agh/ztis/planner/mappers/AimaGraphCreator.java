package pl.edu.agh.ztis.planner.mappers;

import aima.core.environment.map.ExtendableMap;
import aima.core.environment.map.Map;
import net.gexf.format.graph.Edge;
import net.gexf.format.graph.Node;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.planners.GraphType;

import java.util.List;

@Component
public class AimaGraphCreator extends PlanningJobCreator<Map> {

    @Override
    protected PlanningProblem<ExtendableMap> createPlanningProblem(List<Node> nodes, List<Edge> edges, String startNode, String endNode) {
        ExtendableMap graph = new ExtendableMap();

        for (Edge edge : edges) {
            graph.addUnidirectionalLink(edge.getSource(), edge.getTarget(), edge.getWeight().doubleValue());
        }

        return new PlanningProblem<>(
                graph,
                new Vertex(startNode),
                new Vertex(endNode)
        );

    }

    @Override
    GraphType graphType() {
        return GraphType.AIMA;
    }
}
