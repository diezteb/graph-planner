package pl.edu.agh.ztis.planner.planners;

import java.util.List;

import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.shortestpath.PrimMinimumSpanningTree;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

@Component("Prim")
public class PrimPlanner implements Planner {

    @Override
    public PlanningResult executePlanning(PlanningProblem problem) {
        PlanningResult result = new PlanningResult();
        PrimMinimumSpanningTree<Vertex, WeightedEdge> prim = new PrimMinimumSpanningTree<Vertex, WeightedEdge>(
                new Factory<Graph<Vertex, WeightedEdge>>() {
                    @Override
                    public Graph<Vertex, WeightedEdge> create() {
                        return new DirectedSparseGraph<Vertex, WeightedEdge>();
                    }
                });
        Graph<Vertex, WeightedEdge> mst = prim.transform(problem.getGraph());
        result.setPath(backtracePath(mst, problem.getStartPoint(), problem.getEndPoint(), problem.getGraph()));
        return result;
    }

    private List<WeightedEdge> backtracePath(Graph<Vertex, WeightedEdge> mst, Vertex startPoint, Vertex endPoint, Graph<Vertex, WeightedEdge> graph) {
        // TODO backtrace path from MST
        return null;
    }
}
