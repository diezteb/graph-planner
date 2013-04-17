package pl.edu.agh.ztis.planner.mappers;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import net.gexf.format.graph.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.ws.PlanningTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ModelMapper {

    @Autowired
    private AlgorithmResolver algorithmResolver;

    public PlanningJob mapPlanningTask(PlanningTask parameters) {
        GraphContent graph = parameters.getGraph().getGraph();
        List<NodeContent> nodes = filterNodes(graph.getAttributesOrNodesOrEdges());
        List<EdgeContent> edges = filterEdges(graph.getAttributesOrNodesOrEdges());
        Map<String, Vertex> vertexMap = new HashMap<>();
        DirectedSparseGraph<Vertex, WeightedEdge> jungGraph = createJungGraph(nodes, edges, vertexMap);

        Planner planner = algorithmResolver.resolveAlgorithm(parameters.getAlgorithm());
        PlanningProblem planningProblem = new PlanningProblem(jungGraph, vertexMap.get(graph.getStart()), vertexMap.get(graph.getEnd()));
        return new PlanningJob(planner, planningProblem);
    }

    private DirectedSparseGraph<Vertex, WeightedEdge> createJungGraph(List<NodeContent> nodes, List<EdgeContent> edges, Map<String, Vertex> vertexMap) {
        DirectedSparseGraph<Vertex, WeightedEdge> jungGraph = new DirectedSparseGraph<>();

        for (NodeContent node : nodes) {
            Vertex vertex = new Vertex(node.getId());
            vertexMap.put(node.getId(), vertex);
            jungGraph.addVertex(vertex);
        }
        for (EdgeContent edge : edges) {
            jungGraph.addEdge(new WeightedEdge(), vertexMap.get(edge.getSource()), vertexMap.get(edge.getTarget()));
        }
        return jungGraph;
    }

    private List<NodeContent> filterNodes(List<Object> attributesOrNodesOrEdges) {
        List<NodeContent> nodes = new ArrayList<>();
        for (Object attributesOrNodesOrEdge : attributesOrNodesOrEdges) {
            if (attributesOrNodesOrEdge instanceof NodesContent) {
                nodes.addAll(((NodesContent) attributesOrNodesOrEdge).getNode());
            }
        }
        return nodes;
    }

    private List<EdgeContent> filterEdges(List<Object> attributesOrNodesOrEdges) {
        List<EdgeContent> edges = new ArrayList<>();
        for (Object attributesOrNodesOrEdge : attributesOrNodesOrEdges) {
            if (attributesOrNodesOrEdge instanceof EdgesContent) {
                edges.addAll(((EdgesContent) attributesOrNodesOrEdge).getEdge());
            }
        }
        return edges;
    }
}
