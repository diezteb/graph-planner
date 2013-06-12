package pl.edu.agh.ztis.planner.mappers;

import org.jgrapht.graph.DefaultWeightedEdge;
import pl.edu.agh.ztis.planner.model.Vertex;

public class JGraphTEdge extends DefaultWeightedEdge {

    public Vertex getStart() {
        return (Vertex) super.getSource();
    }

    public Vertex getEnd() {
        return (Vertex) super.getTarget();
    }

    public double getWeight() {
        return super.getWeight();
    }
}