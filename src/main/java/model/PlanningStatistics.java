package model;

import java.util.HashMap;
import java.util.Map;

import measures.MeasureType;

public class PlanningStatistics {

    private final Map<MeasureType, Double> statistics = new HashMap<MeasureType, Double>();

    public void combine(PlanningStatistics partialStatistics) {
        getStatistics().putAll(partialStatistics.getStatistics());
    }

    public void add(MeasureType type, double value) {
        getStatistics().put(type, value);
    }

    private Map<MeasureType, Double> getStatistics() {
        return statistics;
    }
}
