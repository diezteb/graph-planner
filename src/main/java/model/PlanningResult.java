package model;

import java.util.List;

import edu.uci.ics.jung.graph.Edge;

public class PlanningResult {
    private List<Edge> path;

    /**
     * @return the path
     */
    public List<Edge> getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(List<Edge> path) {
        this.path = path;
    }

}
