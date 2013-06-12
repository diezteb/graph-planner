package pl.edu.agh.ztis.planner.planners.impl;

import org.fest.assertions.Assertions;
import org.junit.Test;
import pl.edu.agh.ztis.planner.mappers.AimaGraphCreator;
import pl.edu.agh.ztis.planner.model.WeightedEdge;

import java.util.List;

import static pl.edu.agh.ztis.planner.planners.impl.GraphHelper.findShortestPath;
import static pl.edu.agh.ztis.planner.planners.impl.GraphHelper.shortestPathForDirectedAndWeighted;
import static pl.edu.agh.ztis.planner.planners.impl.GraphHelper.shortestPathForDirectedUnweighted;

public class BFSPlannerTest {

    @Test
    public void shouldFindShortestPath() {
        AimaGraphCreator graphCreator = new AimaGraphCreator();
        BFSPlanner tested = new BFSPlanner();

        List<WeightedEdge> foundPath = findShortestPath(tested, graphCreator);
        Assertions.assertThat(foundPath).containsExactly(shortestPathForDirectedUnweighted());
    }
}
