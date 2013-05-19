package pl.edu.agh.ztis.planner.planners.impl;

import edu.uci.ics.jung.algorithms.shortestpath.PrimMinimumSpanningTree;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import org.apache.commons.collections15.Factory;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.List;

@PlannerComponent(algorithm = PlanningAlgorithm.PRIM)
public class PrimPlanner implements Planner<Graph<Vertex, WeightedEdge>> {

    @Override
    public PlanningResult executePlanning(PlanningProblem<Graph<Vertex, WeightedEdge>> problem) {
        PrimMinimumSpanningTree<Vertex, WeightedEdge> prim = new PrimMinimumSpanningTree<>(
                new Factory<Graph<Vertex, WeightedEdge>>() {
                    @Override
                    public Graph<Vertex, WeightedEdge> create() {
                        return new DirectedSparseGraph<>();
                    }
                });
        Graph<Vertex, WeightedEdge> mst = prim.transform(problem.getGraph());
        return new PlanningResult(backtracePath(mst, problem.getStartPoint(), problem.getEndPoint(), problem.getGraph()));
    }

    private List<WeightedEdge> backtracePath(Graph<Vertex, WeightedEdge> mst, Vertex startPoint, Vertex endPoint, Graph<Vertex, WeightedEdge> graph) {
        // TODO backtrace path from MST
        return null;
    }

    @Override
    public GraphType graphType() {
        return GraphType.JUNG;
    }
}
