package pl.edu.agh.ztis.planner.mappers;

import net.gexf.format.graph.*;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.ws.ResponseService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class PlanningJobCreator<T> {

    @SuppressWarnings("unchecked")
    public PlanningJob<? extends T> createPlanningJob(Planner<?> planner, Graph graph, ResponseService responseService) {
        List<Node> nodes = filterNodes(graph.getAttributesAndNodesAndEdges());
        List<Edge> edges = filterEdges(graph.getAttributesAndNodesAndEdges());

        return new PlanningJob<>((Planner<T>) planner, createPlanningProblem(nodes, edges, graph.getStart(), graph.getEnd()), responseService);
    }

    protected abstract PlanningProblem<? extends T> createPlanningProblem(List<Node> nodes, List<Edge> edges, String startNode, String endNode);

    abstract GraphType graphType();

    private List<Node> filterNodes(List<Serializable> attributesOrNodesOrEdges) {
        List<Node> nodes = new ArrayList<>();
        for (Object attributesOrNodesOrEdge : attributesOrNodesOrEdges) {
            if (attributesOrNodesOrEdge instanceof Nodes) {
                nodes.addAll(((Nodes) attributesOrNodesOrEdge).getNodes());
            }
        }
        return nodes;
    }

    private List<Edge> filterEdges(List<Serializable> attributesOrNodesOrEdges) {
        List<Edge> edges = new ArrayList<>();
        for (Object attributesOrNodesOrEdge : attributesOrNodesOrEdges) {
            if (attributesOrNodesOrEdge instanceof Edges) {
                edges.addAll(((Edges) attributesOrNodesOrEdge).getEdges());
            }
        }
        return edges;
    }

}
