package pl.edu.agh.ztis.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.ztis.planner.model.ExecutionResult;

@Controller
@RequestMapping("/results")
public class ResultsController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleResponse(@RequestBody Object result) {
        System.out.println("result = " + result);
        ModelAndView mav = new ModelAndView();
        return mav;
    }
}
