package pl.edu.agh.ztis.planner.planners.impl;

import org.jgrapht.Graph;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import pl.edu.agh.ztis.planner.mappers.JGraphTEdge;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.List;

@PlannerComponent(algorithm = PlanningAlgorithm.FLOYD_WARSHALL)
public class FloydWarshallPlanner extends JGraphTPlanner {

    @Override
    protected List<JGraphTEdge> findPath(PlanningProblem<? extends Graph<Vertex, JGraphTEdge>> problem) {
        FloydWarshallShortestPaths<Vertex, JGraphTEdge> algorithm = new FloydWarshallShortestPaths<>(problem.getGraph());
        return algorithm.getShortestPath(problem.getStartPoint(), problem.getEndPoint()).getEdgeList();
    }
}
