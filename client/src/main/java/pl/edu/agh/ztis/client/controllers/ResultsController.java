package pl.edu.agh.ztis.client.controllers;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import net.gexf.format.graph.Gexf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.ztis.client.PlanningJob;
import pl.edu.agh.ztis.client.ResultsHolder;
import pl.edu.agh.ztis.planner.model.ExecutionResult;
import pl.edu.agh.ztis.planner.model.MeasureType;
import pl.edu.agh.ztis.planner.model.WeightedEdge;

@Controller
@RequestMapping("/results")
public class ResultsController {

    @Autowired
    private ResultsHolder resultsHolder;

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void handleResponse(@RequestBody ExecutionResult result) {
        resultsHolder.storeResult(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{jobId}")
    public ModelAndView viewResults(@PathVariable String jobId) {
        ModelAndView mav = new ModelAndView("results");
        PlanningJob planningJob = resultsHolder.getPlanningJob(jobId);
        mav.addObject("planningJob", planningJob);
        mav.addObject("time", planningJob.getResult().getStatistics().getStatistics().get(MeasureType.EXECUTION_TIME));
        mav.addObject("memory", planningJob.getResult().getStatistics().getStatistics().get(MeasureType.HEAP_MEMORY));
        mav.addObject("pathLength", planningJob.getResult().getStatistics().getStatistics().get(MeasureType.PATH_LENGTH));
        mav.addObject("path", preparePath(planningJob));
        mav.addObject("startNode", planningJob.getTask().getGraph().getGraph().getStart());
        mav.addObject("endNode", planningJob.getTask().getGraph().getGraph().getEnd());
        return mav;
    }

    private String preparePath(PlanningJob planningJob) {
        return Joiner.on(", ").join(
                Lists.transform(planningJob.getResult().getResult().getPath(), new Function<WeightedEdge, String>() {
                    @Override
                    public String apply(WeightedEdge from) {
                        return "'" + from.getStart().getId() + "#" + from.getEnd().getId() + "'";
                    }
                }));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{jobId}/graph", produces = "application/xml")
    public
    @ResponseBody
    Gexf getGraph(@PathVariable String jobId) {
        PlanningJob planningJob = resultsHolder.getPlanningJob(jobId);
        return planningJob.getTask().getGraph();
    }
}
