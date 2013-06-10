package pl.edu.agh.ztis.planner.model;

public class PlanningProblem<T> {
    private T graph;
    private Vertex startPoint;
    private Vertex endPoint;

    public PlanningProblem(T graph, Vertex startPoint, Vertex endPoint) {
        this.graph = graph;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public T getGraph() {
        return graph;
    }

    public Vertex getStartPoint() {
        return startPoint;
    }

    public Vertex getEndPoint() {
        return endPoint;
    }

}
