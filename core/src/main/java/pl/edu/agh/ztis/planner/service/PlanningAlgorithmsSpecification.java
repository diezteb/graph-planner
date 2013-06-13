package pl.edu.agh.ztis.planner.service;

import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

public enum PlanningAlgorithmsSpecification {
    A_STAR(PlanningAlgorithm.A_STAR, true, true),
    BELLMAN_FORD(PlanningAlgorithm.BELLMAN_FORD, true, true),
    BFS(PlanningAlgorithm.BFS, true, false),
    DFS(PlanningAlgorithm.DFS, false, false),
    DIJKSTRA(PlanningAlgorithm.DIJKSTRA, true, true),
    FLOYD_WARSHALL(PlanningAlgorithm.FLOYD_WARSHALL, true, true),
    GREEDY_BEST_FIRST(PlanningAlgorithm.GREEDY_BEST_FIRST, true, false),
    KRUSKAL(PlanningAlgorithm.KRUSKAL, false, true),
    PRIM(PlanningAlgorithm.PRIM, false, true),
    UNIFORM_COST(PlanningAlgorithm.UNIFORM_COST, true, true);

    private PlanningAlgorithm algorithm;
    private boolean supportsDirected;
    private boolean supportsWeighted;

    private PlanningAlgorithmsSpecification(PlanningAlgorithm algorithm, boolean supportsDirected, boolean supportsWeighted) {
        this.algorithm = algorithm;
        this.supportsDirected = supportsDirected;
        this.supportsWeighted = supportsWeighted;
    }


    public static PlanningAlgorithmsSpecification getAlgorithmSpecification(PlanningAlgorithm algorithm) {
        for (PlanningAlgorithmsSpecification specification : values()) {
            if (specification.algorithm == algorithm) {
                return specification;
            }
        }
        return null;
    }

    public boolean supportsWeightedGraph() {
        return supportsWeighted;
    }

    public boolean supportsDirectedGraph() {
        return supportsDirected;
    }
}
