package edu.duke.erss.ups.Controller;

import java.util.ArrayList;
import java.util.List;

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
public class MainPageController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("search", new Search());
        model.addAttribute("results", new ArrayList<Result>());
       
        return "MainPage";
    }

    @PostMapping("/search")
    public ModelAndView  search(@ModelAttribute Search search, Model model) {
        ArrayList<Result> results = new ArrayList<Result>();
        results.add(new Result("1", "a", "created"));
        results.add(new Result("2", "b", "created"));
        results.add(new Result("3", "c", "created"));
        ModelAndView mv=new ModelAndView();
        mv.setViewName("MainPage");
        mv.addObject("results", results);
        
        return mv;
    }

    @RequestMapping("/toLogin")
    public String toLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    
}