package pl.edu.agh.ztis.planner.planners;

import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;

@Component("Dijkstra")
public class DijkstraPlanner implements Planner {

    @Override
    public PlanningResult executePlanning(PlanningProblem problem) {
        PlanningResult result = new PlanningResult();
        DijkstraShortestPath<Vertex, WeightedEdge> paths = new DijkstraShortestPath<Vertex, WeightedEdge>(problem.getGraph());
        result.setPath(paths.getPath(problem.getStartPoint(), problem.getEndPoint()));
        return result;
    }
}
