package pl.edu.agh.ztis.planner.mappers;

import net.gexf.format.graph.EdgeContent;
import net.gexf.format.graph.NodeContent;
import org.jgrapht.Graph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JGraphTGraphCreator extends PlanningJobCreator<Graph<Vertex, WeightedEdge>> {

    @Override
    protected PlanningProblem<Graph<Vertex, WeightedEdge>> createPlanningProblem(List<NodeContent> nodes, List<EdgeContent> edges, String startNode, String endNode) {
        Map<String, Vertex> vertexMap = new HashMap<>();
        Graph<Vertex, WeightedEdge> graph = new SimpleDirectedWeightedGraph<>(new ClassBasedEdgeFactory<Vertex, WeightedEdge>(WeightedEdge.class));

        for (NodeContent node : nodes) {
            Vertex vertex = new Vertex(node.getId());
            vertexMap.put(node.getId(), vertex);
            graph.addVertex(vertex);
        }

        for (EdgeContent edge : edges) {
            graph.addEdge(vertexMap.get(edge.getSource()), vertexMap.get(edge.getTarget()));
        }

        return new PlanningProblem<>(
                graph,
                vertexMap.get(startNode),
                vertexMap.get(endNode)
        );
    }

    @Override
    GraphType graphType() {
        return GraphType.JGRAPHT;
    }
}
