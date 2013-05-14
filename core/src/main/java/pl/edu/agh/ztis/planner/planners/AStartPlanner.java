package pl.edu.agh.ztis.planner.planners;

import org.geotools.graph.path.AStarShortestPathFinder;
import org.geotools.graph.path.Path;
import org.geotools.graph.structure.Graph;
import org.geotools.graph.structure.Node;
import org.geotools.graph.traverse.standard.AStarIterator.AStarFunctions;
import org.geotools.graph.traverse.standard.AStarIterator.AStarNode;
import org.springframework.stereotype.Component;

import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;

@Component("AStar")
public class AStartPlanner implements Planner {

    @Override
    public PlanningResult executePlanning(PlanningProblem problem) {
        Graph graph = getGraph(problem.getGraph());
        Node startPoint = getNode(problem.getStartPoint());
        Node endPoint = getNode(problem.getStartPoint());
        AStarShortestPathFinder astar = new AStarShortestPathFinder(graph, startPoint, endPoint, new AStartHeuristic(graph, endPoint));
        astar.calculate();
        try {
            return getPath(astar.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private PlanningResult getPath(Path path) {
        // TODO Auto-generated method stub
        return null;
    }

    private Node getNode(Vertex startPoint) {
        // TODO Auto-generated method stub
        return null;
    }

    private Graph getGraph(edu.uci.ics.jung.graph.Graph<Vertex, WeightedEdge> graph) {
        // TODO Auto-generated method stub
        return null;
    }

    private static final class AStartHeuristic extends AStarFunctions {
        private AStartHeuristic(Graph graph, Node end) {
            super(end);
        }

        @Override
        public double h(Node arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double cost(AStarNode arg0, AStarNode arg1) {
            // TODO Auto-generated method stub
            return 0;
        }
    }
}
