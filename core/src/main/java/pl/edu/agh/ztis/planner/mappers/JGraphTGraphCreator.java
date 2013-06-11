package pl.edu.agh.ztis.planner.mappers;

import net.gexf.format.graph.Edge;
import net.gexf.format.graph.Node;
import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.planners.GraphType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JGraphTGraphCreator extends PlanningJobCreator<Graph<Vertex, JGraphTEdge>> {

    @Override
    protected PlanningProblem<WeightedGraph<Vertex, JGraphTEdge>> createPlanningProblem(List<Node> nodes, List<Edge> edges, String startNode, String endNode) {
        Map<String, Vertex> vertexMap = new HashMap<>();
        WeightedGraph<Vertex, JGraphTEdge> graph = new SimpleDirectedWeightedGraph<>(new ClassBasedEdgeFactory<Vertex, JGraphTEdge>(JGraphTEdge.class));

        for (Node node : nodes) {
            Vertex vertex = new Vertex(node.getId());
            vertexMap.put(node.getId(), vertex);
            graph.addVertex(vertex);
        }

        for (Edge edge : edges) {
            Vertex start = vertexMap.get(edge.getSource());
            Vertex end = vertexMap.get(edge.getTarget());
            JGraphTEdge weightedEdge = new JGraphTEdge();
            graph.addEdge(start, end, weightedEdge);
            graph.setEdgeWeight(weightedEdge, edge.getWeight());
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
