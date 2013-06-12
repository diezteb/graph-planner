package pl.edu.agh.ztis.client.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.ztis.client.GeneratorType;
import pl.edu.agh.ztis.client.GraphCreator;
import pl.edu.agh.ztis.client.ServiceInvoker;
import pl.edu.agh.ztis.client.controllers.beans.GeneratorFormBean;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;
import pl.edu.agh.ztis.planner.ws.PlanningTaskResponse;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ServiceInvoker serviceInvoker;

    @Autowired
    private GraphCreator graphCreator;

    @ModelAttribute("formBean")
    public GeneratorFormBean createBean() {
        return new GeneratorFormBean();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayForm() {
        ModelAndView mav = new ModelAndView("input");
        List<String> availableTypes = new ArrayList<>(getAvailableGeneratorTypes());
        mav.addObject("availableTypes", availableTypes);
        mav.addObject("algorithms", PlanningAlgorithm.values());
        return mav;
    }

    private Collection<String> getAvailableGeneratorTypes() {
        return Collections2.transform(Arrays.asList(GeneratorType.values()), new Function<GeneratorType, String>() {
            @Override
            public String apply(GeneratorType type) {
                return type.toString();
            }
        });
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public ModelAndView generateAndInvoke(@ModelAttribute("formBean") GeneratorFormBean bean) {
        PlanningTaskResponse response = serviceInvoker.invoke(
                graphCreator.createGraph(bean.getVertices(), bean.getEdges(), GeneratorType.valueOf(bean.getType())),
                PlanningAlgorithm.valueOf(bean.getAlgorithm()));
        ModelAndView mav = new ModelAndView("sentToExecute");
        mav.addObject("message", response);
        return mav;
    }
}
