package pl.edu.agh.ztis.planner.planners.impl;

import org.fest.assertions.Assertions;
import org.junit.Test;
import pl.edu.agh.ztis.planner.mappers.AimaGraphCreator;
import pl.edu.agh.ztis.planner.mappers.JungGraphCreator;
import pl.edu.agh.ztis.planner.model.WeightedEdge;

import java.util.List;

import static pl.edu.agh.ztis.planner.planners.impl.GraphHelper.findShortestPath;
import static pl.edu.agh.ztis.planner.planners.impl.GraphHelper.shortestPath;

public class UniformCostPlannerTest {

    @Test
    public void shouldFindShortestPath() {
        AimaGraphCreator graphCreator = new AimaGraphCreator();
        UniformCostPlanner tested = new UniformCostPlanner();

        List<WeightedEdge> foundPath = findShortestPath(tested, graphCreator);
        Assertions.assertThat(foundPath).containsExactly(shortestPath());
    }
}
