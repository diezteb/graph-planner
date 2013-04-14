package model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Vertex;

public class PlanningProblem {
    private Graph graph;
    private Vertex startPoint;
    private Vertex endPoint;

    /**
     * @return the graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @param graph
     *            the graph to set
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * @return the startPoint
     */
    public Vertex getStartPoint() {
        return startPoint;
    }

    /**
     * @param startPoint
     *            the startPoint to set
     */
    public void setStartPoint(Vertex startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * @return the endPoint
     */
    public Vertex getEndPoint() {
        return endPoint;
    }

    /**
     * @param endPoint
     *            the endPoint to set
     */
    public void setEndPoint(Vertex endPoint) {
        this.endPoint = endPoint;
    }
}
