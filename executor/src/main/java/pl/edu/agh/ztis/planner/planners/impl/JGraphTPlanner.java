package pl.edu.agh.ztis.planner.planners.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.jgrapht.Graph;
import pl.edu.agh.ztis.planner.mappers.JGraphTEdge;
import pl.edu.agh.ztis.planner.model.PlanningProblem;
import pl.edu.agh.ztis.planner.model.PlanningResult;
import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;

import java.util.List;

public abstract class JGraphTPlanner implements Planner<Graph<Vertex, JGraphTEdge>> {

    @Override
    public final PlanningResult executePlanning(PlanningProblem<? extends Graph<Vertex, JGraphTEdge>> problem) {
        List<JGraphTEdge> path = findPath(problem);
        List<WeightedEdge> edges = transformResult(path);
        return new PlanningResult(edges);
    }

    @Override
    public GraphType graphType() {
        return GraphType.JGRAPHT;
    }

    protected abstract List<JGraphTEdge> findPath(PlanningProblem<? extends Graph<Vertex, JGraphTEdge>> problem);

    protected List<WeightedEdge> transformResult(List<JGraphTEdge> path) {
        return Lists.transform(path, new Function<JGraphTEdge, WeightedEdge>() {
            @Override
            public WeightedEdge apply(JGraphTEdge defaultWeightedEdge) {
                return new WeightedEdge(defaultWeightedEdge.getStart(), defaultWeightedEdge.getEnd(), defaultWeightedEdge.getWeight());
            }
        });
    }
}
