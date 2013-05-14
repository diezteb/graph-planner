package pl.edu.agh.ztis.planner.measures;

import pl.edu.agh.ztis.planner.model.PlanningResult;

public class MemoryMeasure extends Measure {

    private final MemoryInstrumentationThread instrumentationThread = new MemoryInstrumentationThread(500);

    @Override
    protected double getValue() {
        return instrumentationThread.getMaxCollectedValue();
    }

    @Override
    protected MeasureType getType() {
        return MeasureType.HEAP_MEMORY;
    }

    @Override
    public void initialize() {
        instrumentationThread.start();
    }

    @Override
    public void finalize(PlanningResult planningResult) {
        instrumentationThread.finish();
    }

}
