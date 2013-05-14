package pl.edu.agh.ztis.planner.planners;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import edu.uci.ics.jung.algorithms.shortestpath.BFSDistanceLabeler;
import edu.uci.ics.jung.graph.Graph;

@Component("BFS")
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
