package pl.edu.agh.ztis.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import net.gexf.format.graph.DefaultedgetypeType;
import net.gexf.format.graph.EdgeContent;
import net.gexf.format.graph.EdgesContent;
import net.gexf.format.graph.GraphContent;
import net.gexf.format.graph.NodeContent;
import net.gexf.format.graph.NodesContent;

import org.apache.commons.collections15.Factory;
import org.springframework.stereotype.Component;

import pl.edu.agh.ztis.planner.model.Vertex;
import pl.edu.agh.ztis.planner.model.WeightedEdge;
import edu.uci.ics.jung.algorithms.generators.GraphGenerator;
import edu.uci.ics.jung.algorithms.generators.random.BarabasiAlbertGenerator;
import edu.uci.ics.jung.algorithms.generators.random.EppsteinPowerLawGenerator;
import edu.uci.ics.jung.algorithms.generators.random.ErdosRenyiGenerator;
import edu.uci.ics.jung.algorithms.generators.random.KleinbergSmallWorldGenerator;
import edu.uci.ics.jung.algorithms.generators.random.MixedRandomGraphGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;

@Component
public class GraphCreator {

    private final Random r = new Random();

    public GraphContent createGraph(int vertices, int edges, GeneratorType generatorType) {
        GraphGenerator<Vertex, WeightedEdge> generator = null;
        Graph<Vertex, WeightedEdge> graph = null;
        switch (generatorType) {
        case BARBASI_ALBERT:
            generator = new BarabasiAlbertGenerator<Vertex, WeightedEdge>(getGraphFactory(), getVertexFactory(), getEdgesfactory(), vertices, edges,
                    new HashSet<Vertex>());
            graph = generator.create();
            break;
        case EPPSTEIN_POWER_LAW:
            generator = new EppsteinPowerLawGenerator<Vertex, WeightedEdge>(getGraphFactory(), getVertexFactory(), getEdgesfactory(), vertices,
                    edges, 100);
            graph = generator.create();
            break;
        case ERDOS_RENYI:
            generator = new ErdosRenyiGenerator<Vertex, WeightedEdge>(getGraphFactoryU(), getVertexFactory(), getEdgesfactory(), vertices, 0.1);
            graph = generator.create();
            break;
        case KLEINBERG_SMALL_WORLD:
            generator = new KleinbergSmallWorldGenerator<Vertex, WeightedEdge>(getGraphFactoryU(), getVertexFactory(), getEdgesfactory(), 2, 0.1);
            graph = generator.create();
            break;
        case MIXED_RANDOM:
            graph = MixedRandomGraphGenerator.<Vertex, WeightedEdge> generateMixedRandomGraph(getGraphFactory(), getVertexFactory(),
                    getEdgesfactory(), new HashMap<WeightedEdge, Number>(), vertices, new HashSet<Vertex>());
        default:
            return createGraph();
        }
        return generate(graph);
    }

    public GraphContent generate(Graph<Vertex, WeightedEdge> input) {
        GraphContent graph = new GraphContent();
        graph.withDefaultedgetype(DefaultedgetypeType.DIRECTED).withAttributesOrNodesOrEdges(getNodesContent(input))
                .withAttributesOrNodesOrEdges(getEdgesContent(input)).withStart(getRandomVertexId(input)).withEnd(getRandomVertexId(input));
        return graph;
    }

    private String getRandomVertexId(Graph<Vertex, WeightedEdge> input) {
        Collection<Vertex> vertices = input.getVertices();
        int vertexCount = input.getVertexCount();
        return vertices.toArray(new Vertex[vertexCount])[r.nextInt(vertexCount)].getId();
    }

    private EdgesContent getEdgesContent(Graph<Vertex, WeightedEdge> input) {
        EdgesContent content = new EdgesContent();
        for (WeightedEdge edge : input.getEdges()) {
            Pair<Vertex> endpoints = input.getEndpoints(edge);
            content.withEdge(edge(endpoints.getFirst().getId(), endpoints.getSecond().getId(), edge.getWeight()));
        }
        return content;
    }

    private NodesContent getNodesContent(Graph<Vertex, WeightedEdge> input) {
        NodesContent content = new NodesContent();
        for (Vertex vertex : input.getVertices()) {
            content.withNode(new NodeContent().withId(vertex.getId()));
        }
        return content;
    }

    private Factory<WeightedEdge> getEdgesfactory() {
        return new Factory<WeightedEdge>() {
            @Override
            public WeightedEdge create() {
                return new WeightedEdge(new Vertex(UUID.randomUUID().toString()), new Vertex(UUID.randomUUID().toString()), r.nextInt(100));
            }
        };
    }

    private Factory<Vertex> getVertexFactory() {
        return new Factory<Vertex>() {
            @Override
            public Vertex create() {
                return new Vertex(UUID.randomUUID().toString());
            }
        };
    }

    private Factory<Graph<Vertex, WeightedEdge>> getGraphFactory() {
        return new Factory<Graph<Vertex, WeightedEdge>>() {
            @Override
            public Graph<Vertex, WeightedEdge> create() {
                return new SparseGraph<Vertex, WeightedEdge>();
            }
        };
    }

    private Factory<UndirectedGraph<Vertex, WeightedEdge>> getGraphFactoryU() {
        return new Factory<UndirectedGraph<Vertex, WeightedEdge>>() {

            @Override
            public UndirectedGraph<Vertex, WeightedEdge> create() {
                return new UndirectedSparseGraph<Vertex, WeightedEdge>();
            }
        };
    }

    public GraphContent createGraph() {
        return new GraphContent()
                .withDefaultedgetype(DefaultedgetypeType.DIRECTED)
                .withAttributesOrNodesOrEdges(
                        new NodesContent().withNode(new NodeContent().withId("1")).withNode(new NodeContent().withId("2"))
                                .withNode(new NodeContent().withId("3")).withNode(new NodeContent().withId("4"))
                                .withNode(new NodeContent().withId("5")).withNode(new NodeContent().withId("6"))
                                .withNode(new NodeContent().withId("7")).withNode(new NodeContent().withId("8"))
                                .withNode(new NodeContent().withId("9")).withNode(new NodeContent().withId("10")))
                .withAttributesOrNodesOrEdges(
                        new EdgesContent().withEdge(edge(1, 2, 1)).withEdge(edge(2, 3, 1)).withEdge(edge(2, 4, 5)).withEdge(edge(2, 5, 1))
                                .withEdge(edge(5, 8, 1)).withEdge(edge(5, 6, 1)).withEdge(edge(4, 6, 1)).withEdge(edge(6, 8, 1))
                                .withEdge(edge(8, 7, 1)).withEdge(edge(7, 9, 1)).withEdge(edge(7, 10, 1)).withEdge(edge(4, 10, 5))
                                .withEdge(edge(10, 1, 0))).withStart("1").withEnd("10");
    }

    private EdgeContent edge(String start, String end, double weight) {
        return new EdgeContent().withSource(start).withTarget(end).withWeight(Double.valueOf(weight).floatValue());
    }

    private EdgeContent edge(int start, int end, float weight) {
        return new EdgeContent().withSource(String.valueOf(start)).withTarget(String.valueOf(end)).withWeight(weight);
    }
}
