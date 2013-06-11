package pl.edu.agh.ztis.client.controllers.beans;


public class GeneratorFormBean {
    private int vertices;
    private int edges;
    private String type;
    private String algorithm;

    /**
     * @return the vertices
     */
    public int getVertices() {
        return vertices;
    }

    /**
     * @return the edges
     */
    public int getEdges() {
        return edges;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param vertices
     *            the vertices to set
     */
    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    /**
     * @param edges
     *            the edges to set
     */
    public void setEdges(int edges) {
        this.edges = edges;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
