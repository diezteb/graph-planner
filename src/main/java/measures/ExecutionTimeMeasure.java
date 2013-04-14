package measures;

import java.util.GregorianCalendar;

import model.PlanningResult;

public class ExecutionTimeMeasure extends Measure {

    private long startTime;
    private long endTime;

    @Override
    public void initialize() {
        startTime = GregorianCalendar.getInstance().getTimeInMillis();
    }

    @Override
    public void finalize(PlanningResult planningResult) {
        endTime = GregorianCalendar.getInstance().getTimeInMillis();
    }

    @Override
    protected double getValue() {
        return endTime - startTime;
    }

    @Override
    protected MeasureType getType() {
        return MeasureType.EXECUTION_TIME;
    }

}
