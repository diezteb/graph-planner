package pl.edu.agh.ztis.planner.measures;

import pl.edu.agh.ztis.planner.model.MeasureType;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.PlanningStatistics;

public abstract class Measure {

    public PlanningStatistics getStatistics() {
        PlanningStatistics statistics = new PlanningStatistics();
        statistics.add(getType(), getValue());
        return statistics;
    }

    public void initialize() {
    }

    public void finalize(PlanningResult planningResult) {
    }

    protected abstract double getValue();

    protected abstract MeasureType getType();

}
