package edu.duke.erss.ups.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.UserTrackingDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.User;

@Controller
public class UserDetailController {
    private TrackingShipDao trackingShipDao;
    private UserTrackingDao userTrackingDao;

    public UserDetailController(TrackingShipDao trackingShipDao, UserTrackingDao userTrackingDao) {
        this.trackingShipDao = trackingShipDao;
        this.userTrackingDao = userTrackingDao;
    }


    @RequestMapping("/userDetail")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        System.out.println("user id is:" + user.getId() + "username:" + user.getName());
        List<ShipInfo> results = trackingShipDao.getShipInfoByUserID(user.getId());
        model.addAttribute("results", results);
        return "userShip";
    }



}
