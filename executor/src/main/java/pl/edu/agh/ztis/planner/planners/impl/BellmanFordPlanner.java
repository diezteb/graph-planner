package pl.edu.agh.ztis.planner.planners.impl;

import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.BellmanFordShortestPath;

import pl.edu.agh.ztis.planner.mappers.JGraphTEdge;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

@PlannerComponent(algorithm = PlanningAlgorithm.BELLMAN_FORD)
public class BellmanFordPlanner extends JGraphTPlanner {

    @Override
    protected List<JGraphTEdge> findPath(PlanningProblem<? extends Graph<Vertex, JGraphTEdge>> problem) {
        List<JGraphTEdge> path = BellmanFordShortestPath.findPathBetween(problem.getGraph(), problem.getStartPoint(), problem.getEndPoint());
        if (path != null) {
            return path;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean supportsWeightedGraph() {
        return true;
    }

    @Override
    public boolean supportsDirectedGraph() {
        return true;
    }
}
