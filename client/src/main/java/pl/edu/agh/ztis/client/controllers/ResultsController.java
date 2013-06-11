package pl.edu.agh.ztis.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.agh.ztis.client.ResultsHolder;
import pl.edu.agh.ztis.planner.model.ExecutionResult;

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
}
