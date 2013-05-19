package pl.edu.agh.ztis.planner.planners.impl;

import org.jgrapht.Graph;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.List;
import java.util.Set;

@PlannerComponent(algorithm = PlanningAlgorithm.KRUSKAL)
public class KruskalPlanner implements Planner<Graph<Vertex, WeightedEdge>> {

    @Override
    public PlanningResult executePlanning(PlanningProblem<Graph<Vertex, WeightedEdge>> problem) {
        KruskalMinimumSpanningTree<Vertex, WeightedEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(problem.getGraph());

        List<WeightedEdge> path = findPath(kruskalMinimumSpanningTree.getEdgeSet());
        return new PlanningResult(path);
    }

    private List<WeightedEdge> findPath(Set<WeightedEdge> edgeSet) {
        return null;
    }

    @Override
    public GraphType graphType() {
        return GraphType.JGRAPHT;
    }
}
