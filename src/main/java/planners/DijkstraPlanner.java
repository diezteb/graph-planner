package planners;

import model.PlanningProblem;
import model.PlanningResult;
import model.Vertex;
import model.WeightedEdge;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;

public class DijkstraPlanner implements Planner {

    @Override
    public PlanningResult executePlanning(PlanningProblem problem) {
        PlanningResult result = new PlanningResult();
        DijkstraShortestPath<Vertex, WeightedEdge> paths = new DijkstraShortestPath<Vertex, WeightedEdge>(problem.getGraph());
        result.setPath(paths.getPath(problem.getStartPoint(), problem.getEndPoint()));
        return result;
    }
}
