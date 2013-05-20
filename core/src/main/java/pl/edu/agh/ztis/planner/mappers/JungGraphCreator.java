package pl.edu.agh.ztis.planner.mappers;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import net.gexf.format.graph.EdgeContent;
import net.gexf.format.graph.NodeContent;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JungGraphCreator extends PlanningJobCreator<Graph<Vertex, WeightedEdge>> {

    @Override
    public PlanningProblem<Graph<Vertex, WeightedEdge>> createPlanningProblem(List<NodeContent> nodes, List<EdgeContent> edges, String startNode, String endNode) {
        Map<String, Vertex> vertexMap = new HashMap<>();
        Graph<Vertex, WeightedEdge> jungGraph = new DirectedSparseGraph<>();

        for (NodeContent node : nodes) {
            Vertex vertex = new Vertex(node.getId());
            vertexMap.put(node.getId(), vertex);
            jungGraph.addVertex(vertex);
        }
        for (EdgeContent edge : edges) {
            Vertex start = vertexMap.get(edge.getSource());
            Vertex end = vertexMap.get(edge.getTarget());
            jungGraph.addEdge(new WeightedEdge(start, end, edge.getWeight()), start, end);
        }

        return new PlanningProblem<>(
                jungGraph,
                vertexMap.get(startNode),
                vertexMap.get(endNode)
        );
    }

    @Override
    GraphType graphType() {
        return GraphType.JUNG;
    }
}
