package edu.duke.erss.ups.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.entity.Result;
import edu.duke.erss.ups.entity.Search;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.User;

@Controller
public class MainController {
    TrackingShipDao trackingShipDao;

    public MainController(TrackingShipDao trackingShipDao) {
        this.trackingShipDao = trackingShipDao;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("search", new Search());
        model.addAttribute("results", new ArrayList<Result>());

        return "MainPage";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Search search, Model model) {
        Long trackingNum = 0L;
        try {
            trackingNum = Long.parseLong(search.getTrackingID());
        } catch (Exception e) {
            model.addAttribute("search", new Search());
            model.addAttribute("results", new ArrayList<ShipInfo>());
            model.addAttribute("message", "Wrong tracking num");
            return "MainPage";
        }
        List<ShipInfo> shipInfos = trackingShipDao.getShipInfoByTrackingID(trackingNum);
        if (shipInfos.size() == 0) {
            model.addAttribute("search", new Search());
            model.addAttribute("results", new ArrayList<ShipInfo>());
            model.addAttribute("message", "Wrong tracking num");
            return "MainPage";
        }

        model.addAttribute("search", new Search());
        model.addAttribute("results", shipInfos);
        return "MainPage";

    }

    @RequestMapping("/toLogin")
    public String toLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/main")
    public String toMain(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("test", "test");
        System.out.println("username" + user.getName() + " pass" + user.getPassword());
        model.addAttribute("search", new Search());
        model.addAttribute("results", new ArrayList<Result>());
        return "MainPage";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        model.addAttribute("search", new Search());
        model.addAttribute("results", new ArrayList<Result>());
        return "MainPage";
    }

}
