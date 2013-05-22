package pl.edu.agh.ztis.planner.planners.impl;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleGraph;
import pl.edu.agh.ztis.planner.mappers.JGraphTEdge;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.List;
import java.util.Set;

@PlannerComponent(algorithm = PlanningAlgorithm.KRUSKAL)
public class KruskalPlanner extends JGraphTPlanner {

    @Override
    protected List<JGraphTEdge> findPath(PlanningProblem<? extends Graph<Vertex, JGraphTEdge>> problem) {
        KruskalMinimumSpanningTree<Vertex, JGraphTEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(problem.getGraph());
        Graph<Vertex, JGraphTEdge> mst = createGraph(kruskalMinimumSpanningTree.getEdgeSet());
        DijkstraShortestPath<Vertex, JGraphTEdge> dijkstra = new DijkstraShortestPath<>(mst, problem.getStartPoint(), problem.getEndPoint());
        return dijkstra.getPathEdgeList();
    }

    private Graph<Vertex, JGraphTEdge> createGraph(Set<JGraphTEdge> edges) {
        Graph<Vertex, JGraphTEdge> graph = new SimpleGraph<>(new ClassBasedEdgeFactory<Vertex, JGraphTEdge>(JGraphTEdge.class));
        for (JGraphTEdge edge : edges) {
            graph.addVertex(edge.getStart());
            graph.addVertex(edge.getEnd());
            graph.addEdge(edge.getStart(), edge.getEnd(), edge);
        }
        return graph;
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
