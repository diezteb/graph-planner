package pl.edu.agh.ztis.planner.mappers;

import net.gexf.format.graph.EdgeContent;
import net.gexf.format.graph.NodeContent;
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
    protected PlanningProblem<WeightedGraph<Vertex, JGraphTEdge>> createPlanningProblem(List<NodeContent> nodes, List<EdgeContent> edges, String startNode, String endNode) {
        Map<String, Vertex> vertexMap = new HashMap<>();
        WeightedGraph<Vertex, JGraphTEdge> graph = new SimpleDirectedWeightedGraph<>(new ClassBasedEdgeFactory<Vertex, JGraphTEdge>(JGraphTEdge.class));

        for (NodeContent node : nodes) {
            Vertex vertex = new Vertex(node.getId());
            vertexMap.put(node.getId(), vertex);
            graph.addVertex(vertex);
        }

        for (EdgeContent edge : edges) {
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
