package planners;

import model.PlanningProblem;
import model.PlanningResult;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;

public class DijkstraPlanner implements Planner {

    @SuppressWarnings("unchecked")
    @Override
    public PlanningResult executePlanning(PlanningProblem problem) {
        PlanningResult result = new PlanningResult();
        DijkstraShortestPath paths = new DijkstraShortestPath(problem.getGraph());
        result.setPath(paths.getPath(problem.getStartPoint(), problem.getEndPoint()));
        return result;
    }
}
