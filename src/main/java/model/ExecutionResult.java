package model;

public class ExecutionResult {
    private PlanningResult result;
    private PlanningStatistics statistics;

    /**
     * @return the result
     */
    public PlanningResult getResult() {
        return result;
    }

    /**
     * @param planningResult
     *            the result to set
     */
    public void setResult(PlanningResult planningResult) {
        this.result = planningResult;
    }

    /**
     * @return the statistics
     */
    public PlanningStatistics getStatistics() {
        return statistics;
    }

    /**
     * @param statistics
     *            the statistics to set
     */
    public void setStatistics(PlanningStatistics statistics) {
        this.statistics = statistics;
    }

}
