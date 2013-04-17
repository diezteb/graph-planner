package pl.edu.agh.ztis.planner.model;

import edu.uci.ics.jung.graph.Graph;

public class PlanningProblem {
    private Graph<Vertex, WeightedEdge> graph;
    private Vertex startPoint;
    private Vertex endPoint;

    public PlanningProblem(Graph<Vertex, WeightedEdge> graph, Vertex startPoint, Vertex endPoint) {
        this.graph = graph;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * @return the graph
     */
    public Graph<Vertex, WeightedEdge> getGraph() {
        return graph;
    }

    /**
     * @return the startPoint
     */
    public Vertex getStartPoint() {
        return startPoint;
    }

    /**
     * @return the endPoint
     */
    public Vertex getEndPoint() {
        return endPoint;
    }

}
