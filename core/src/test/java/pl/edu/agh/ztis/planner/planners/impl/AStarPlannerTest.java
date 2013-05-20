package pl.edu.agh.ztis.planner.planners.impl;

import org.fest.assertions.Assertions;
import org.junit.Test;
import pl.edu.agh.ztis.planner.mappers.AimaGraphCreator;
import pl.edu.agh.ztis.planner.model.WeightedEdge;

import java.util.List;

import static pl.edu.agh.ztis.planner.planners.impl.GraphHelper.findShortestPath;
import static pl.edu.agh.ztis.planner.planners.impl.GraphHelper.shortestPath;

public class AStarPlannerTest {

    @Test
    public void shouldFindShortestPath() throws Exception {
        AimaGraphCreator graphCreator = new AimaGraphCreator();
        AStarPlanner tested = new AStarPlanner();

        List<WeightedEdge> foundPath = findShortestPath(tested, graphCreator);
        Assertions.assertThat(foundPath).containsExactly(shortestPath());
    }

}
