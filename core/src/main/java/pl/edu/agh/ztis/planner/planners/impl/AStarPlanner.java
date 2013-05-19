package pl.edu.agh.ztis.planner.planners.impl;

import org.geotools.graph.path.AStarShortestPathFinder;
import org.geotools.graph.path.Path;
import org.geotools.graph.structure.Graph;
import org.geotools.graph.structure.Node;
import org.geotools.graph.traverse.standard.AStarIterator.AStarFunctions;
import org.geotools.graph.traverse.standard.AStarIterator.AStarNode;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

@PlannerComponent(algorithm = PlanningAlgorithm.A_STAR)
public class AStarPlanner implements Planner<Graph> {

    @Override
    public PlanningResult executePlanning(PlanningProblem<Graph> problem) {
        Node startPoint = getNode(problem.getStartPoint());
        Node endPoint = getNode(problem.getStartPoint());
        AStarShortestPathFinder astar = new AStarShortestPathFinder(problem.getGraph(), startPoint, endPoint, new AStartHeuristic(problem.getGraph(), endPoint));
        astar.calculate();
        try {
            return getPath(astar.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GraphType graphType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
