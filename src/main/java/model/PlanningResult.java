package model;

import java.util.List;

public class PlanningResult {
    private List<WeightedEdge> path;

    /**
     * @return the path
     */
    public List<WeightedEdge> getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(List<WeightedEdge> path) {
        this.path = path;
    }

}
