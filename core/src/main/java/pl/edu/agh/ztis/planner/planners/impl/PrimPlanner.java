package pl.edu.agh.ztis.planner.planners.impl;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.algorithms.shortestpath.PrimMinimumSpanningTree;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;
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
    public PlanningResult executePlanning(PlanningProblem<? extends Graph<Vertex, WeightedEdge>> problem) {
        Transformer<WeightedEdge, Double> weightTransformer = new Transformer<WeightedEdge, Double>() {
            @Override
            public Double transform(WeightedEdge weightedEdge) {
                return weightedEdge.getWeight();
            }
        };
        Factory<Graph<Vertex, WeightedEdge>> mstFactory = new Factory<Graph<Vertex, WeightedEdge>>() {
            @Override
            public Graph<Vertex, WeightedEdge> create() {
                return new SparseGraph<>();
            }
        };
        PrimMinimumSpanningTree<Vertex, WeightedEdge> prim = new PrimMinimumSpanningTree<>(
                mstFactory, weightTransformer
        );
        Graph<Vertex, WeightedEdge> mst = prim.transform(problem.getGraph());
        return new PlanningResult(backtracePath(mst, problem.getStartPoint(), problem.getEndPoint()));
    }

    private List<WeightedEdge> backtracePath(Graph<Vertex, WeightedEdge> mst, Vertex startPoint, Vertex endPoint) {
        DijkstraShortestPath<Vertex, WeightedEdge> paths = new DijkstraShortestPath<>(mst);
        return paths.getPath(startPoint, endPoint);
    }

    @Override
    public GraphType graphType() {
        return GraphType.JUNG;
    }

    @Override
    public boolean supportsWeightedGraph() {
        return true;
    }

    @Override
    public boolean supportsDirectedGraph() {
        return false;
    }
}
