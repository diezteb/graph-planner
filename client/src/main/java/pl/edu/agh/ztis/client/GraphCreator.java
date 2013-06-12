package pl.edu.agh.ztis.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.gexf.format.graph.DefaultedgetypeType;
import net.gexf.format.graph.Edge;
import net.gexf.format.graph.Edges;
import net.gexf.format.graph.Node;
import net.gexf.format.graph.Nodes;

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

    public net.gexf.format.graph.Graph createGraph(int vertices, int edges, GeneratorType generatorType) {
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
                    edges, 5);
            graph = generator.create();
            break;
        case ERDOS_RENYI:
            generator = new ErdosRenyiGenerator<Vertex, WeightedEdge>(getGraphFactoryU(), getVertexFactory(), getEdgesfactory(), vertices, edges
                    / (vertices * vertices * 1.0));
            graph = generator.create();
            break;
        case KLEINBERG_SMALL_WORLD:
            generator = new KleinbergSmallWorldGenerator<Vertex, WeightedEdge>(getGraphFactory(), getVertexFactory(), getEdgesfactory(),
                    (int) Math.sqrt(vertices), 0.1);
            graph = generator.create();
            break;
        case MIXED_RANDOM:
            graph = MixedRandomGraphGenerator.<Vertex, WeightedEdge> generateMixedRandomGraph(getGraphFactory(), getVertexFactory(),
                    getEdgesfactory(), new HashMap<WeightedEdge, Number>(), vertices, new HashSet<Vertex>());
            break;
        case RANDOM_CONNECTED:
            return createConnectedGraph(vertices, edges);
        case SAMPLE:
        default:
            return createGraph();
        }
        return generate(graph);
    }

    public net.gexf.format.graph.Graph generate(Graph<Vertex, WeightedEdge> input) {
        net.gexf.format.graph.Graph graph = new net.gexf.format.graph.Graph();
        graph.withDefaultedgetype(DefaultedgetypeType.DIRECTED).withAttributesAndNodesAndEdges(getNodesContent(input))
                .withAttributesAndNodesAndEdges(getEdgesContent(input)).withStart(getRandomVertexId(input)).withEnd(getRandomVertexId(input));
        return graph;
    }

    private String getRandomVertexId(Graph<Vertex, WeightedEdge> input) {
        Collection<Vertex> vertices = input.getVertices();
        int vertexCount = input.getVertexCount();
        return vertices.toArray(new Vertex[vertexCount])[r.nextInt(vertexCount)].getId();
    }

    private Edges getEdgesContent(Graph<Vertex, WeightedEdge> input) {
        int edgeId = 1;
        Edges content = new Edges();
        for (WeightedEdge edge : input.getEdges()) {
            Pair<Vertex> endpoints = input.getEndpoints(edge);
            content.withEdges(edge(endpoints.getFirst().getId(), endpoints.getSecond().getId(), edge.getWeight(), String.valueOf(edgeId++)));
        }
        return content;
    }

    private Nodes getNodesContent(Graph<Vertex, WeightedEdge> input) {
        Nodes content = new Nodes();
        for (Vertex vertex : input.getVertices()) {
            content.withNodes(new Node().withId(vertex.getId()));
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

    public net.gexf.format.graph.Graph createGraph() {
        return new net.gexf.format.graph.Graph()
                .withDefaultedgetype(DefaultedgetypeType.DIRECTED)
                .withAttributesAndNodesAndEdges(
                        new Nodes().withNodes(new Node().withId("1"), new Node().withId("2"), new Node().withId("3"), new Node().withId("4"),
                                new Node().withId("5"), new Node().withId("6"), new Node().withId("7"), new Node().withId("8"),
                                new Node().withId("9"), new Node().withId("10")))
                .withAttributesAndNodesAndEdges(
                        new Edges().withEdges(edge(1, 2, 1, 1), edge(2, 3, 1, 2), edge(2, 4, 5, 3), edge(2, 5, 1, 4), edge(5, 8, 1, 5),
                                edge(5, 6, 1, 6), edge(4, 6, 1, 7), edge(6, 8, 1, 8), edge(8, 7, 1, 9), edge(7, 9, 1, 10), edge(7, 10, 1, 11),
                                edge(4, 10, 5, 12), edge(10, 1, 0, 13))).withStart("1").withEnd("10");
    }

    private net.gexf.format.graph.Graph createConnectedGraph(int vertices, int edges) {
        int start = r.nextInt(vertices - 1);
        int end = start + r.nextInt(vertices - start);
        return new net.gexf.format.graph.Graph().withDefaultedgetype(DefaultedgetypeType.DIRECTED)
                .withAttributesAndNodesAndEdges(createVertices(vertices)).withAttributesAndNodesAndEdges(createEdges(vertices, edges))
                .withStart(Integer.valueOf(start).toString()).withEnd(Integer.valueOf(end).toString());
    }

    private Edges createEdges(int vertices, int edgesNumber) {
        List<Edge> edges = new ArrayList<Edge>();
        if (vertices > 1) {
            int skip = 1;
            int start = 0;
            for (int i = 0; i < edgesNumber; i++) {
                int end = (start + skip) % vertices;
                edges.add(edge(start, end, r.nextInt(100), i));
                start++;
                if (start > vertices) {
                    start = 0;
                    skip += 1;
                }
            }
        }
        return new Edges().withEdges(edges);
    }

    private Nodes createVertices(int vertices) {
        List<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < vertices; i++) {
            nodes.add(new Node().withId(Integer.valueOf(i).toString()));
        }
        return new Nodes().withNodes(nodes);
    }

    private Edge edge(String start, String end, double weight, String id) {
        return new Edge().withId(id).withSource(start).withTarget(end).withWeight(Double.valueOf(weight).floatValue());
    }

    private Edge edge(int start, int end, float weight, int id) {
        return new Edge().withId(String.valueOf(id)).withSource(String.valueOf(start)).withTarget(String.valueOf(end)).withWeight(weight);
    }
}
