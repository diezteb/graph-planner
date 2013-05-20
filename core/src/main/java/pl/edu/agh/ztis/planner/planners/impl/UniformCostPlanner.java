package pl.edu.agh.ztis.planner.planners.impl;

import aima.core.environment.map.Map;
import aima.core.search.framework.Search;
import aima.core.search.uninformed.UniformCostSearch;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

@PlannerComponent(algorithm = PlanningAlgorithm.UNIFORM_COST)
public class UniformCostPlanner extends AimaPlanner {

    @Override
    protected Search createSearch(PlanningProblem<? extends Map> planningProblem) {
        return new UniformCostSearch();
    }
}
