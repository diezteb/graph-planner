package pl.edu.agh.ztis.planner.model;


public class WeightedEdge {
    private Vertex start;
    private Vertex end;
    private double weight;

    public WeightedEdge(Vertex start, Vertex end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeightedEdge that = (WeightedEdge) o;

        return end.equals(that.end) && start.equals(that.start);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}