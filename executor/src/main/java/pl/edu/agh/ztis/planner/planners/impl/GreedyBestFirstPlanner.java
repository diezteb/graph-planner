package pl.edu.agh.ztis.planner.planners.impl;

import aima.core.environment.map.Map;
import aima.core.environment.map.StraightLineDistanceHeuristicFunction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Search;
import aima.core.search.informed.GreedyBestFirstSearch;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

@PlannerComponent(algorithm = PlanningAlgorithm.GREEDY_BEST_FIRST)
public class GreedyBestFirstPlanner extends AimaPlanner {
    @Override
    protected Search createSearch(PlanningProblem<? extends Map> planningProblem) {
        return new GreedyBestFirstSearch(new GraphSearch(),
                new StraightLineDistanceHeuristicFunction(
                        planningProblem.getEndPoint().getId(), planningProblem.getGraph()));
    }

    @Override
    public boolean supportsWeightedGraph() {
        return false;
    }

    @Override
    public boolean supportsDirectedGraph() {
        return true;
    }
}
