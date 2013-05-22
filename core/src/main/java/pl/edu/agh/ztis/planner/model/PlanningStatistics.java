package pl.edu.agh.ztis.planner.model;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.ztis.planner.measures.MeasureType;

public class PlanningStatistics {

    private final Map<MeasureType, Double> statistics = new HashMap<MeasureType, Double>();

    public void combine(PlanningStatistics partialStatistics) {
        getStatistics().putAll(partialStatistics.getStatistics());
    }

    public void add(MeasureType type, double value) {
        getStatistics().put(type, value);
    }

    public Map<MeasureType, Double> getStatistics() {
        return statistics;
    }
}
