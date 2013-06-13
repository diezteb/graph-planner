package pl.edu.agh.ztis.planner.service;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import net.gexf.format.graph.Edge;
import net.gexf.format.graph.Edges;
import net.gexf.format.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.ws.*;

import javax.jws.WebParam;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Iterables.*;
import static net.gexf.format.graph.DefaultedgetypeType.DIRECTED;

@Component
public class PlanningServiceImpl implements GraphPlanningPortType {

    @Autowired
    private PlanningJobScheduler scheduler;

    @Override
    public PlanningTaskResponse schedulePlanning(
            @WebParam(partName = "parameters", name = "planning-task", targetNamespace = "http://agh.edu.pl/ztis/planning") PlanningTask parameters) {

        String jobId = UUID.randomUUID().toString();
        ResponseStatus status;
        if (isValidRequest(parameters)) {
            scheduler.scheduleForExecution(jobId, parameters);
            status = ResponseStatus.OK;
        } else {
            status = ResponseStatus.UNSUPPORTED;
        }
        return new PlanningTaskResponse().withStatus(status).withJobId(jobId);
    }

    private boolean isValidRequest(PlanningTask parameters) {
        PlanningAlgorithm algorithm = parameters.getAlgorithm();
        PlanningAlgorithmsSpecification algorithmSpecification = PlanningAlgorithmsSpecification.getAlgorithmSpecification(algorithm);
        Graph graph = parameters.getGraph().getGraph();
        return !((DIRECTED.equals(graph.getDefaultedgetype()) && !algorithmSpecification.supportsDirectedGraph())
                || (isWeightedGraph(graph) && !algorithmSpecification.supportsWeightedGraph()));
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
