package pl.edu.agh.ztis.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.ztis.client.GraphCreator;
import pl.edu.agh.ztis.client.ServiceInvoker;
import pl.edu.agh.ztis.planner.ws.PlanningTaskResponse;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ServiceInvoker serviceInvoker;

    @Autowired
    private GraphCreator graphCreator;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printWelcome(ModelMap model) {
        ModelAndView mav = new ModelAndView("hello.jsp");
        PlanningTaskResponse response = serviceInvoker.invoke(graphCreator.createGraph());
        mav.addObject("message", response.getStatus());
        return mav;
    }
}
