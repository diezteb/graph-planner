package pl.edu.agh.ztis.planner.mappers;

import net.gexf.format.graph.*;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;

import java.util.ArrayList;
import java.util.List;

public abstract class PlanningJobCreator<T> {

    @SuppressWarnings("unchecked")
    public PlanningJob<? extends T> createPlanningJob(Planner<?> planner, GraphContent graph) {
        List<NodeContent> nodes = filterNodes(graph.getAttributesOrNodesOrEdges());
        List<EdgeContent> edges = filterEdges(graph.getAttributesOrNodesOrEdges());

        return new PlanningJob<>((Planner<T>) planner, createPlanningProblem(nodes, edges, graph.getStart(), graph.getEnd()));
    }

    protected abstract PlanningProblem<? extends T> createPlanningProblem(List<NodeContent> nodes, List<EdgeContent> edges, String startNode, String endNode);

    abstract GraphType graphType();

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
