package pl.edu.agh.ztis.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printWelcome(ModelMap model) {
        ModelAndView mav = new ModelAndView("hello.jsp");
        mav.addObject("message", "test");
        return mav;
    }
}
