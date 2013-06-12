package pl.edu.agh.ztis.planner.planners.impl;

import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;

import pl.edu.agh.ztis.planner.mappers.JGraphTEdge;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

@PlannerComponent(algorithm = PlanningAlgorithm.FLOYD_WARSHALL)
public class FloydWarshallPlanner extends JGraphTPlanner {

    @Override
    protected List<JGraphTEdge> findPath(PlanningProblem<? extends Graph<Vertex, JGraphTEdge>> problem) {
        FloydWarshallShortestPaths<Vertex, JGraphTEdge> algorithm = new FloydWarshallShortestPaths<>(problem.getGraph());
        GraphPath<Vertex, JGraphTEdge> shortestPath = algorithm.getShortestPath(problem.getStartPoint(), problem.getEndPoint());
        if (shortestPath != null) {
            return shortestPath.getEdgeList();
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
