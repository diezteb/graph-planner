package pl.edu.agh.ztis.planner.measures;

import pl.edu.agh.ztis.planner.model.MeasureType;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.WeightedEdge;

public class PathLengthMeasure extends Measure {

    private double pathLength;

    @Override
    public void finalize(PlanningResult planningResult) {
        double sum = 0;
        for (WeightedEdge e : planningResult.getPath()) {
            sum += e.getWeight();
        }
        pathLength = sum;
    }

    @Override
    protected double getValue() {
        return pathLength;
    }

    @Override
    protected MeasureType getType() {
        return MeasureType.PATH_LENGTH;
    }
}
