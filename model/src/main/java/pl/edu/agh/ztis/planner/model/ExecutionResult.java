package pl.edu.agh.ztis.planner.model;

public class ExecutionResult {
    private PlanningResult result;
    private PlanningStatistics statistics;
    private String jobId;

    public PlanningResult getResult() {
        return result;
    }

    public void setResult(PlanningResult planningResult) {
        this.result = planningResult;
    }

    public PlanningStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(PlanningStatistics statistics) {
        this.statistics = statistics;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
