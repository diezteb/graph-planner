package planners;

import model.PlanningProblem;
import model.PlanningResult;

public interface Planner {

    PlanningResult executePlanning(PlanningProblem problem);
}
