package planners;

import java.util.ArrayList;
import java.util.List;

import model.PlanningProblem;
import model.PlanningResult;
import model.Vertex;
import model.WeightedEdge;
import edu.uci.ics.jung.algorithms.shortestpath.BFSDistanceLabeler;
import edu.uci.ics.jung.graph.Graph;

public class BFSPlanner implements Planner {

    @Override
    public PlanningResult executePlanning(PlanningProblem problem) {
        PlanningResult result = new PlanningResult();
        BFSDistanceLabeler<Vertex, WeightedEdge> bfs = new BFSDistanceLabeler<Vertex, WeightedEdge>();
        bfs.labelDistances(problem.getGraph(), problem.getStartPoint());
        result.setPath(backtracePath(bfs, problem.getStartPoint(), problem.getEndPoint(), problem.getGraph()));
        return result;

    }

    private List<WeightedEdge> backtracePath(BFSDistanceLabeler<Vertex, WeightedEdge> bfs, Vertex startPoint, Vertex endPoint,
            Graph<Vertex, WeightedEdge> graph) {
        List<WeightedEdge> path = new ArrayList<WeightedEdge>();
        int distance = bfs.getDistance(graph, endPoint);
        // TODO calculate path from end to start point by checking predecesors
        return null;
    }
}
