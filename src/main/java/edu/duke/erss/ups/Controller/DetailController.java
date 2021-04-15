package edu.duke.erss.ups.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.entity.ShipInfo;


@Controller
public class DetailController {
    private TrackingShipDao trackingShipDao;
    public DetailController(TrackingShipDao trackingShipDao){
        this.trackingShipDao = trackingShipDao;
    }

    @RequestMapping("/detail")
    public String index(Model model, String id) {
        System.out.println("traking id is " + id);
        List<ShipInfo> shipInfo = trackingShipDao.getShipInfoByTrackingID(Long.parseLong(id));
        model.addAttribute("result", shipInfo.get(0));
        return "detail";
    }

    @PostMapping("/changeDestination")
    public String changeDestination(@RequestParam("x") String x, @RequestParam("y") String y) {
        System.out.println("change destination: " + "x: " +  x + "y: " + y);
        return "redirect:/";
    }
}
