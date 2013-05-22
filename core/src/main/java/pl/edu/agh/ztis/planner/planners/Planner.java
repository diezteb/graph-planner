package pl.edu.agh.ztis.planner.planners;

import net.gexf.format.graph.DefaultedgetypeType;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;

public interface Planner<T> {

    PlanningResult executePlanning(PlanningProblem<? extends T> problem);

    GraphType graphType();

    boolean supportsWeightedGraph();

    boolean supportsDirectedGraph();
}
