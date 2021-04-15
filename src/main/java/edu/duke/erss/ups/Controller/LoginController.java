package edu.duke.erss.ups.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.duke.erss.ups.entity.Result;
import edu.duke.erss.ups.entity.Search;
import edu.duke.erss.ups.entity.User;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String index(Model model) {
        model.addAttribute("user", new User());
        //model.addAttribute("message", "hello");
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute User user) {
        //validate
        ArrayList<Result> results = new ArrayList<>();
        results.add(new Result("1", "a", "created"));
        results.add(new Result("2", "b", "created"));
        results.add(new Result("3", "c", "created"));
        ModelAndView mv=new ModelAndView();
        mv.setViewName("MainPage");
        mv.addObject("results", results);
        mv.addObject("search", new Search());
        return mv;
       
    }
}
