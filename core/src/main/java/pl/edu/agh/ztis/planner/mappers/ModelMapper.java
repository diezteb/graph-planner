package pl.edu.agh.ztis.planner.mappers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import net.gexf.format.graph.Edge;
import net.gexf.format.graph.Edges;
import net.gexf.format.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.executors.task.PlanningJob;
import pl.edu.agh.ztis.planner.planners.GraphType;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.ws.PlanningTask;

import java.util.List;

import static com.google.common.collect.Iterables.*;
import static net.gexf.format.graph.DefaultedgetypeType.DIRECTED;

@Component
public class ModelMapper {

    @Autowired
    private AlgorithmResolver algorithmResolver;

    @Autowired
    private List<PlanningJobCreator<?>> graphCreators;

    public PlanningJob<?> mapPlanningTask(PlanningTask parameters) {
        Graph graph = parameters.getGraph().getGraph();
        Planner<?> planner = algorithmResolver.resolveAlgorithm(parameters.getAlgorithm());
        validateProblem(graph, planner);
        PlanningJobCreator<?> graphCreator = findGraphCreator(planner.graphType());
        return graphCreator.createPlanningJob(planner, graph, parameters.getResponseService());
    }

    private void validateProblem(Graph graph, Planner<?> planner) {
        if ((DIRECTED.equals(graph.getDefaultedgetype()) && !planner.supportsDirectedGraph()) || (isWeightedGraph(graph) && !planner.supportsWeightedGraph())) {
            throw new IllegalArgumentException("Graph type unsupported by chosen algorithm");
        }
    }

    private PlanningJobCreator<?> findGraphCreator(final GraphType graphType) {
        return find(graphCreators, new Predicate<PlanningJobCreator<?>>() {
            public boolean apply(PlanningJobCreator<?> graphCreator) {
                return graphCreator.graphType().equals(graphType);
            }
        });
    }

    private boolean isWeightedGraph(Graph graph) {
        return tryFind(concat(transform(filter(graph.getAttributesAndNodesAndEdges(), Edges.class), new Function<Edges, List<Edge>>() {
            @Override
            public List<Edge> apply(Edges edgesContent) {
                return edgesContent.getEdges();
            }
        })), new Predicate<Edge>() {
            @Override
            public boolean apply(Edge input) {
                return input.getWeight() != null;
            }
        }).isPresent();
    }
}
