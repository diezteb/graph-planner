package pl.edu.agh.ztis.planner.measures;

public class MemoryInstrumentationThread extends Thread {

    private boolean keepRunning = true;
    private long maxHeapValue = 0;
    private final int step;

    public MemoryInstrumentationThread(int step) {
        this.step = step;
    }

    @Override
    public void run() {
        while (keepRunning) {
            try {
                long heap = Runtime.getRuntime().totalMemory();
                if (heap > maxHeapValue) {
                    maxHeapValue = heap;
                }
                Thread.sleep(step);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getMaxCollectedValue() {
        return maxHeapValue;
    }

    public void finish() {
        keepRunning = false;
    }

}
