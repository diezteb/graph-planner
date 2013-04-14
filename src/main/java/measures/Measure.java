package measures;

import model.PlanningResult;
import model.PlanningStatistics;

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
