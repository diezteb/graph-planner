package pl.edu.agh.ztis.planner.measures;

import pl.edu.agh.ztis.planner.model.MeasureType;
import pl.edu.agh.ztis.planner.model.PlanningResult;

public class MemoryMeasure extends Measure {

    private final MemoryInstrumentationThread instrumentationThread = new MemoryInstrumentationThread(1);
    private long initialMemory;

    @Override
    protected double getValue() {
        long maxCollected = instrumentationThread.getMaxCollectedValue();
        if (maxCollected > 0) {
            return maxCollected - initialMemory;
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
        Runtime rt = Runtime.getRuntime();
        initialMemory = (rt.totalMemory() - rt.freeMemory());
        instrumentationThread.start();
    }

    @Override
    public void finalize(PlanningResult planningResult) {
        instrumentationThread.finish();
    }

}
