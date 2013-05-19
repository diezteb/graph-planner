package pl.edu.agh.ztis.planner.planners.impl;

import org.jgrapht.Graph;
import org.jgrapht.alg.BellmanFordShortestPath;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.List;

@PlannerComponent(algorithm = PlanningAlgorithm.BELLMAN_FORD)
public class BellmanFordPlanner implements Planner<Graph<Vertex, WeightedEdge>> {

    @Override
    public PlanningResult executePlanning(PlanningProblem<Graph<Vertex, WeightedEdge>> problem) {
        List<WeightedEdge> path = BellmanFordShortestPath.findPathBetween(problem.getGraph(), problem.getStartPoint(), problem.getEndPoint());
        return new PlanningResult(path);
    }

    @Override
    public GraphType graphType() {
        return GraphType.JGRAPHT;
    }

}
