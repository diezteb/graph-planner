package pl.edu.agh.ztis.planner.planners.impl;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.jgrapht.Graph;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import pl.edu.agh.ztis.planner.mappers.JGraphTEdge;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@PlannerComponent(algorithm = PlanningAlgorithm.KRUSKAL)
public class KruskalPlanner implements Planner<Graph<Vertex, JGraphTEdge>> {

    @Override
    public PlanningResult executePlanning(PlanningProblem<? extends Graph<Vertex, JGraphTEdge>> problem) {
        KruskalMinimumSpanningTree<Vertex, JGraphTEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(problem.getGraph());

        List<WeightedEdge> path = findPath(problem, kruskalMinimumSpanningTree.getEdgeSet());
        return new PlanningResult(path);
    }

    private List<WeightedEdge> findPath(PlanningProblem<? extends Graph<Vertex, JGraphTEdge>> problem, Set<JGraphTEdge> edgeSet) {
        Map<Vertex, JGraphTEdge> endVertexToEdgeMap = Maps.uniqueIndex(edgeSet, new Function<JGraphTEdge, Vertex>() {
            @Override
            public Vertex apply(JGraphTEdge edge) {
                return edge.getEnd();
            }
        });
        Vertex nextPoint = problem.getEndPoint();
        List<WeightedEdge> path = new ArrayList<>();
        Vertex startPoint = problem.getStartPoint();
        while (!nextPoint.equals(startPoint)) {
            JGraphTEdge edge = endVertexToEdgeMap.get(nextPoint);
            nextPoint = edge.getStart();
            path.add(new WeightedEdge(edge.getStart(), edge.getEnd(), edge.getWeight()));
        }
        return path;
    }

    @Override
    public GraphType graphType() {
        return GraphType.JGRAPHT;
    }
}
