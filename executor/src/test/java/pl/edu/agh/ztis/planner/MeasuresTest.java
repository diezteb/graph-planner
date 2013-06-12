package pl.edu.agh.ztis.planner;

import java.util.Map.Entry;

import org.junit.Test;

import pl.edu.agh.ztis.planner.executors.SimpleExecutor;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.mappers.AimaGraphCreator;
import pl.edu.agh.ztis.planner.measures.AggregateMeasure;
import pl.edu.agh.ztis.planner.measures.ExecutionTimeMeasure;
import pl.edu.agh.ztis.planner.model.MeasureType;
import pl.edu.agh.ztis.planner.measures.MemoryMeasure;
import pl.edu.agh.ztis.planner.measures.PathLengthMeasure;
import pl.edu.agh.ztis.planner.model.ExecutionResult;
import pl.edu.agh.ztis.planner.planners.impl.AStarPlanner;
import pl.edu.agh.ztis.planner.planners.impl.GraphHelper;

public class MeasuresTest {

    @Test
    public void testMeasures() throws Exception {
        AimaGraphCreator graphCreator = new AimaGraphCreator();
        AStarPlanner tested = new AStarPlanner();

        AggregateMeasure measure = new AggregateMeasure();
        measure.addMeasure(new ExecutionTimeMeasure());
        measure.addMeasure(new MemoryMeasure());
        measure.addMeasure(new PathLengthMeasure());

        PlanningJob job = graphCreator.createPlanningJob(tested, GraphHelper.createGraph(), null);

        SimpleExecutor executor = new SimpleExecutor();
        ExecutionResult executionResult = executor.execute(job, measure);
        for (Entry<MeasureType, Double> entry : executionResult.getStatistics().getStatistics().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
