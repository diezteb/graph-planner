package pl.edu.agh.ztis.planner.planners.impl;

import aima.core.agent.Action;
import aima.core.environment.map.Map;
import aima.core.environment.map.MapFunctionFactory;
import aima.core.environment.map.MapStepCostFunction;
import aima.core.environment.map.MoveToAction;
import aima.core.search.framework.DefaultGoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.filter;

public abstract class AimaPlanner implements Planner<Map> {

    @Override
    public PlanningResult executePlanning(PlanningProblem<? extends Map> planningProblem) {
        try {
            Map graph = planningProblem.getGraph();
            Problem problem = createProblem(planningProblem, graph);

            Search search = createSearch(planningProblem);

            SearchAgent agent = new SearchAgent(problem, search);
            List<Action> actions = agent.getActions();

            return new PlanningResult(mapEdges(actions, graph, planningProblem.getStartPoint()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Search createSearch(PlanningProblem<? extends Map> planningProblem);

    protected Problem createProblem(PlanningProblem<? extends Map> planningProblem, Map graph) {
        return new Problem(planningProblem.getStartPoint().getId(),
                MapFunctionFactory.getActionsFunction(graph),
                MapFunctionFactory.getResultFunction(), new DefaultGoalTest(
                planningProblem.getEndPoint().getId()),
                new MapStepCostFunction(graph));
    }

    protected List<WeightedEdge> mapEdges(List<Action> actions, Map graph, Vertex startPoint) {
        String previousPoint = startPoint.getId();
        Iterable<MoveToAction> path = filter(actions, MoveToAction.class);
        List<WeightedEdge> edges = new ArrayList<>();
        for (MoveToAction moveToAction : path) {
            String nextPoint = moveToAction.getToLocation();
            WeightedEdge weightedEdge = new WeightedEdge(new Vertex(previousPoint), new Vertex(nextPoint), graph.getDistance(previousPoint, nextPoint));
            edges.add(weightedEdge);
            previousPoint = nextPoint;
        }
        return edges;
    }

    @Override
    public GraphType graphType() {
        return GraphType.AIMA;
    }
}
