package pl.edu.agh.ztis.planner.planners.impl;

import net.gexf.format.graph.*;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.mappers.PlanningJobCreator;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.Planner;

import java.util.List;

public class GraphHelper {
    public static Graph createGraph() {
        return new Graph()
                .withDefaultedgetype(DefaultedgetypeType.DIRECTED)
                .withAttributesAndNodesAndEdges(new Nodes().withNodes(
                        new Node().withId("1"),
                        new Node().withId("2"),
                        new Node().withId("3"),
                        new Node().withId("4"),
                        new Node().withId("5"),
                        new Node().withId("6"),
                        new Node().withId("7"),
                        new Node().withId("8"),
                        new Node().withId("9"),
                        new Node().withId("10")
                )).withAttributesAndNodesAndEdges(new Edges().withEdges(
                        edge(1, 2, 1),
                        edge(2, 3, 1),
                        edge(2, 4, 5),
                        edge(2, 5, 1),
                        edge(5, 8, 1),
                        edge(5, 6, 1),
                        edge(4, 6, 1),
                        edge(6, 8, 1),
                        edge(8, 7, 1),
                        edge(7, 9, 1),
                        edge(7, 10, 1),
                        edge(4, 10, 5),
                        edge(10, 1, 0)
                )).withStart("1")
                .withEnd("10");
    }

    private static Edge edge(int start, int end, float weight) {
        return new Edge()
                .withSource(String.valueOf(start))
                .withTarget(String.valueOf(end))
                .withWeight(weight);
    }

    public static WeightedEdge edge(String start, String end) {
        return new WeightedEdge(new Vertex(start), new Vertex(end), 1);
    }


    public static WeightedEdge[] shortestPathForDirectedAndWeighted() {
        return new WeightedEdge[]{
                edge("1", "2"),
                edge("2", "5"),
                edge("5", "8"),
                edge("8", "7"),
                edge("7", "10")
        };
    }

    public static WeightedEdge[] shortestPathForDirectedUnweighted() {
        return new WeightedEdge[]{
                edge("1", "2"),
                edge("2", "4"),
                edge("4", "10")
        };
    }

    public static WeightedEdge[] shortestPathForUndirectedWeighted() {
        return new WeightedEdge[]{
                edge("10", "1")
        };
    }

    public static <T> List<WeightedEdge> findShortestPath(Planner<T> tested, PlanningJobCreator<T> graphCreator) {
        Graph graph = GraphHelper.createGraph();
        PlanningJob<? extends T> planningJob = graphCreator.createPlanningJob(tested, graph, null);

        PlanningResult result = planningJob.call();
        return result.getPath();
    }
}
