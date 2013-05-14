package pl.edu.agh.ztis.planner.measures;

import pl.edu.agh.ztis.planner.model.PlanningResult;

public class MemoryMeasure extends Measure {

    private final MemoryInstrumentationThread instrumentationThread = new MemoryInstrumentationThread(500);
    private long initialMemory;
    private long finalMemory;

    @Override
    protected double getValue() {
        return instrumentationThread.getMaxCollectedValue() - (initialMemory + finalMemory) / 2;
    }

    @Override
    protected MeasureType getType() {
        return MeasureType.HEAP_MEMORY;
    }

    @Override
    public void initialize() {
        initialMemory = Runtime.getRuntime().totalMemory();
        instrumentationThread.start();
    }

    @Override
    public void finalize(PlanningResult planningResult) {
        instrumentationThread.finish();
        finalMemory = Runtime.getRuntime().totalMemory();
    }

}
