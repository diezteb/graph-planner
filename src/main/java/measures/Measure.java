package measures;

import model.PlanningResult;
import model.PlanningStatistics;

public interface Measure {

    public PlanningStatistics getStatistics();

    public void initialize();

    public void finalize(PlanningResult planningResult);

}
