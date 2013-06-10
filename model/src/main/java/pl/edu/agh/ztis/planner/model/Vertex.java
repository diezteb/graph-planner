package pl.edu.agh.ztis.planner.model;

public class Vertex {
    private String id;

    public Vertex(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return id.equals(vertex.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id='" + id + '\'' +
                '}';
    }
}
