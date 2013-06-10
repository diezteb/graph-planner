package pl.edu.agh.ztis.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/results")
public class ResultsController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView printWelcome(ModelMap model) {
        ModelAndView mav = new ModelAndView("hello.jsp");
        return mav;
    }
}
