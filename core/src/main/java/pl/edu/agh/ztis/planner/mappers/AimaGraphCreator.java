package pl.edu.agh.ztis.planner.mappers;

import aima.core.environment.map.ExtendableMap;
import aima.core.environment.map.Map;
import net.gexf.format.graph.EdgeContent;
import net.gexf.format.graph.NodeContent;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.planners.GraphType;

import java.util.List;

@Component
public class AimaGraphCreator extends PlanningJobCreator<Map> {

    @Override
    protected PlanningProblem<ExtendableMap> createPlanningProblem(List<NodeContent> nodes, List<EdgeContent> edges, String startNode, String endNode) {
        ExtendableMap graph = new ExtendableMap();

//        for (NodeContent node : nodes) {
//            Vertex vertex = new Vertex(node.getId());
//            vertexMap.put(node.getId(), vertex);
//        }
        for (EdgeContent edge : edges) {
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
