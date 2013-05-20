package pl.edu.agh.ztis.planner.planners.impl;

import net.gexf.format.graph.*;
import org.fest.assertions.Assertions;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.mappers.PlanningJobCreator;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.Planner;

import java.util.List;

public class GraphHelper {
    public static GraphContent createGraph() {
        return new GraphContent()
                .withAttributesOrNodesOrEdges(new NodesContent()
                        .withNode(new NodeContent().withId("1"))
                        .withNode(new NodeContent().withId("2"))
                        .withNode(new NodeContent().withId("3"))
                        .withNode(new NodeContent().withId("4"))
                        .withNode(new NodeContent().withId("5"))
                        .withNode(new NodeContent().withId("6"))
                        .withNode(new NodeContent().withId("7"))
                        .withNode(new NodeContent().withId("8"))
                        .withNode(new NodeContent().withId("9"))
                        .withNode(new NodeContent().withId("10"))
                ).withAttributesOrNodesOrEdges(new EdgesContent()
                        .withEdge(edge(1, 2, 1))
                        .withEdge(edge(2, 3, 1))
                        .withEdge(edge(2, 4, 5))
                        .withEdge(edge(2, 5, 1))
                        .withEdge(edge(5, 8, 1))
                        .withEdge(edge(5, 6, 1))
                        .withEdge(edge(4, 6, 1))
                        .withEdge(edge(6, 8, 1))
                        .withEdge(edge(8, 7, 1))
                        .withEdge(edge(7, 9, 1))
                        .withEdge(edge(7, 10, 1))
                        .withEdge(edge(4, 10, 5))
                ).withStart("1")
                .withEnd("10");
    }

    private static EdgeContent edge(int start, int end, float weight) {
        return new EdgeContent()
                .withSource(String.valueOf(start))
                .withTarget(String.valueOf(end))
                .withWeight(weight);
    }

    public static WeightedEdge edge(String start, String end) {
        return new WeightedEdge(new Vertex(start), new Vertex(end), 1);
    }


    public static WeightedEdge[] shortestPath() {
        return new WeightedEdge[]{
                edge("1", "2"),
                edge("2", "5"),
                edge("5", "8"),
                edge("8", "7"),
                edge("7", "10")
        };
    }

    public static <T> List<WeightedEdge> findShortestPath(Planner<T> tested, PlanningJobCreator<T> graphCreator) {
        GraphContent graph = GraphHelper.createGraph();
        PlanningJob<? extends T> planningJob = graphCreator.createPlanningJob(tested, graph);

        PlanningResult result = planningJob.call();
        return result.getPath();
    }
}
