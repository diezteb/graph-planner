package pl.edu.agh.ztis.planner.planners.impl;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.List;

@PlannerComponent(algorithm = PlanningAlgorithm.DIJKSTRA)
public class DijkstraPlanner implements Planner<Graph<Vertex, WeightedEdge>> {

    @Override
    public PlanningResult executePlanning(PlanningProblem<Graph<Vertex, WeightedEdge>> problem) {
        DijkstraShortestPath<Vertex, WeightedEdge> paths = new DijkstraShortestPath<>(problem.getGraph());
        List<WeightedEdge> path = paths.getPath(problem.getStartPoint(), problem.getEndPoint());
        return new PlanningResult(path);
    }

    @Override
    public GraphType graphType() {
        return GraphType.JUNG;
    }
}
