package pl.edu.agh.ztis.planner.planners.impl;

import edu.uci.ics.jung.algorithms.shortestpath.BFSDistanceLabeler;
import edu.uci.ics.jung.graph.Graph;
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

@PlannerComponent(algorithm = PlanningAlgorithm.BFS)
public class BFSPlanner implements Planner<Graph<Vertex, WeightedEdge>> {

    @Override
    public PlanningResult executePlanning(PlanningProblem<Graph<Vertex, WeightedEdge>> problem) {
        BFSDistanceLabeler<Vertex, WeightedEdge> bfs = new BFSDistanceLabeler<>();
        bfs.labelDistances(problem.getGraph(), problem.getStartPoint());
        return new PlanningResult(backtracePath(bfs, problem.getStartPoint(), problem.getEndPoint(), problem.getGraph()));
    }

    private List<WeightedEdge> backtracePath(BFSDistanceLabeler<Vertex, WeightedEdge> bfs, Vertex startPoint, Vertex endPoint,
                                             Graph<Vertex, WeightedEdge> graph) {
        List<WeightedEdge> path = new ArrayList<>();
        int distance = bfs.getDistance(graph, endPoint);
        // TODO calculate path from end to start point by checking predecesors
        return null;
    }

    @Override
    public GraphType graphType() {
        return GraphType.JUNG;
    }
}
