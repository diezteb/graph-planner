package pl.edu.agh.ztis.planner.measures;

import pl.edu.agh.ztis.planner.model.MeasureType;
import pl.edu.agh.ztis.planner.model.PlanningResult;

public class MemoryMeasure extends Measure {

    private final MemoryInstrumentationThread instrumentationThread = new MemoryInstrumentationThread(500);
    private long initialMemory;
    private long finalMemory;

    @Override
    protected double getValue() {
        long maxCollected = instrumentationThread.getMaxCollectedValue();
        if (maxCollected > 0) {
            return maxCollected - (initialMemory + finalMemory) / 2;
        } else { // when thread wasn't even able to start
            return maxCollected;
        }
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
