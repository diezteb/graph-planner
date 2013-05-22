package pl.edu.agh.ztis.planner.planners.impl;

import aima.core.environment.map.Map;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Node;
import aima.core.search.framework.Search;
import aima.core.search.uninformed.BreadthFirstSearch;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.planners.discovery.PlannerComponent;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.Comparator;

@PlannerComponent(algorithm = PlanningAlgorithm.BFS)
public class BFSPlanner extends AimaPlanner {

    @Override
    protected Search createSearch(PlanningProblem<? extends Map> planningProblem) {
        return new BreadthFirstSearch(new GraphSearch());
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