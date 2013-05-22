package pl.edu.agh.ztis.planner.measures;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.PlanningStatistics;

public class AggregateMeasure extends Measure {

    private final List<Measure> measures = new ArrayList<Measure>();

    @Override
    public PlanningStatistics getStatistics() {
        PlanningStatistics statistics = new PlanningStatistics();
        for (Measure m : measures) {
            statistics.combine(m.getStatistics());
        }
        return statistics;
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    @Override
    public void initialize() {
        for (Measure m : measures) {
            m.initialize();
        }
    }

    @Override
    public void finalize(PlanningResult planningResult) {
        for (Measure m : measures) {
            m.finalize(planningResult);
        }
    }

    @Override
    protected double getValue() {
        return -1;
    }

    @Override
    protected MeasureType getType() {
        return MeasureType.AGGREGATE;
    }
}
