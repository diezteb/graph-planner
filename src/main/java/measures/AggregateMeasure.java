package measures;

import java.util.List;

import model.PlanningResult;
import model.PlanningStatistics;

import org.springframework.beans.factory.annotation.Autowired;

public class AggregateMeasure extends Measure {

    @Autowired
    private List<Measure> measures;

    @Override
    public PlanningStatistics getStatistics() {
        PlanningStatistics statistics = new PlanningStatistics();
        for (Measure m : measures) {
            statistics.combine(m.getStatistics());
        }
        return statistics;
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
