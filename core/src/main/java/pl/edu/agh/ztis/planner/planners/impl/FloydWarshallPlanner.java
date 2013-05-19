package pl.edu.agh.ztis.planner.planners.impl;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

@PlannerComponent(algorithm = PlanningAlgorithm.FLOYD_WARSHALL)
public class FloydWarshallPlanner implements Planner<Graph<Vertex, WeightedEdge>> {
    @Override
    public PlanningResult executePlanning(PlanningProblem<Graph<Vertex, WeightedEdge>> problem) {
        FloydWarshallShortestPaths<Vertex, WeightedEdge> floydWarshallShortestPaths = new FloydWarshallShortestPaths<>(problem.getGraph());
        GraphPath<Vertex, WeightedEdge> shortestPath = floydWarshallShortestPaths.getShortestPath(problem.getStartPoint(), problem.getEndPoint());
        return new PlanningResult(shortestPath.getEdgeList());
    }

    @Override
    public GraphType graphType() {
        return GraphType.JGRAPHT;
    }
}
