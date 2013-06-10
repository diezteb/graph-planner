package pl.edu.agh.ztis.client;

import net.gexf.format.graph.*;
import org.springframework.stereotype.Component;

@Component
public class GraphCreator {

    public GraphContent createGraph() {
        return new GraphContent()
                .withDefaultedgetype(DefaultedgetypeType.DIRECTED)
                .withAttributesOrNodesOrEdges(new NodesContent()
                        .withNode(new NodeContent().withId("1"))
                        .withNode(new NodeContent().withId("2"))
                        .withNode(new NodeContent().withId("3"))
                        .withNode(new NodeContent().withId("4"))
                        .withNode(new NodeContent().withId("5"))
                        .withNode(new NodeContent().withId("6"))
                        .withNode(new NodeContent().withId("7"))
                        .withNode(new NodeContent().withId("8"))
                        .withNode(new NodeContent().withId("9"))
                        .withNode(new NodeContent().withId("10"))
                ).withAttributesOrNodesOrEdges(new EdgesContent()
                        .withEdge(edge(1, 2, 1))
                        .withEdge(edge(2, 3, 1))
                        .withEdge(edge(2, 4, 5))
                        .withEdge(edge(2, 5, 1))
                        .withEdge(edge(5, 8, 1))
                        .withEdge(edge(5, 6, 1))
                        .withEdge(edge(4, 6, 1))
                        .withEdge(edge(6, 8, 1))
                        .withEdge(edge(8, 7, 1))
                        .withEdge(edge(7, 9, 1))
                        .withEdge(edge(7, 10, 1))
                        .withEdge(edge(4, 10, 5))
                        .withEdge(edge(10, 1, 0))
                ).withStart("1")
                .withEnd("10");
    }

    private EdgeContent edge(int start, int end, float weight) {
        return new EdgeContent()
                .withSource(String.valueOf(start))
                .withTarget(String.valueOf(end))
                .withWeight(weight);
    }
}
