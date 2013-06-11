package pl.edu.agh.ztis.planner.model;

import java.util.List;

public class PlanningResult {
    private List<WeightedEdge> path;

    public PlanningResult() {
    }

    public PlanningResult(List<WeightedEdge> path) {
        this.path = path;
    }

    public List<WeightedEdge> getPath() {
        return path;
    }

}
