package pl.edu.agh.ztis.planner.mappers;

import com.google.common.base.Predicate;
import net.gexf.format.graph.GraphContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.ws.PlanningTask;

import java.util.List;

import static com.google.common.collect.Iterables.find;

@Component
public class ModelMapper {

    @Autowired
    private AlgorithmResolver algorithmResolver;

    @Autowired
    private List<PlanningJobCreator<?>> graphCreators;

    public PlanningJob<?> mapPlanningTask(PlanningTask parameters) {
        GraphContent graph = parameters.getGraph().getGraph();

        Planner<?> planner = algorithmResolver.resolveAlgorithm(parameters.getAlgorithm());
        PlanningJobCreator<?> graphCreator = findGraphCreator(planner.graphType());
        return graphCreator.createPlanningJob(planner, graph);
    }

    private PlanningJobCreator<?> findGraphCreator(final GraphType graphType) {
        return find(graphCreators, new Predicate<PlanningJobCreator<?>>() {
            public boolean apply(PlanningJobCreator<?> graphCreator) {
                return graphCreator.graphType().equals(graphType);
            }
        });
    }
}
