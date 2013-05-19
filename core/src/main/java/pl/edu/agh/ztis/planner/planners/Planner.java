package pl.edu.agh.ztis.planner.planners;

import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;

public interface Planner<T> {

    PlanningResult executePlanning(PlanningProblem<T> problem);

    GraphType graphType();
}
