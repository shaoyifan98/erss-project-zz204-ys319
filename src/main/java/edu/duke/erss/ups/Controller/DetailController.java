package edu.duke.erss.ups.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.duke.erss.ups.MailController;
import edu.duke.erss.ups.WorldController;
import edu.duke.erss.ups.dao.ProductDao;
import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.TruckDao;
import edu.duke.erss.ups.entity.Product;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.Truck;
import edu.duke.erss.ups.entity.User;

@Controller
public class DetailController {
    private TrackingShipDao trackingShipDao;
    private ProductDao productDao;
    private TruckDao truckDao;
    private WorldController worldController;
    private MailController mailController;

    public DetailController(TrackingShipDao trackingShipDao, ProductDao productDao, TruckDao truckDao,
            WorldController worldController, MailController mailController) {
        this.trackingShipDao = trackingShipDao;
        this.productDao = productDao;
        this.truckDao = truckDao;
        this.worldController = worldController;
        this.mailController = mailController;
    }

    @RequestMapping("/detail")
    public String index(Model model, String id, HttpServletRequest request) {
        System.out.println("traking id is " + id);
        HttpSession session = request.getSession();
        session.setAttribute("currTrackingID", Long.parseLong(id));
        List<ShipInfo> shipInfo = trackingShipDao.getShipInfoByTrackingID(Long.parseLong(id));
        List<Product> products = productDao.getProductByShipID(shipInfo.get(0).getShipID());
        model.addAttribute("result", shipInfo.get(0));
        model.addAttribute("products", products);
        return "detail";
    }

    @RequestMapping("/changeDestination")
    public String toChangeDestination(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("id is" + session.getAttribute("currTrackingID"));
        return "changeDestination";
    }

    @PostMapping("/changeDestination")
    public String changeDestination(Model model, @RequestParam("x") String x, @RequestParam("y") String y,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long trackingID = (Long) session.getAttribute("currTrackingID");
        System.out.println("id is" + trackingID);
        int _x = 0;
        int _y = 0;
        try {
            _x = Integer.parseInt(x);
            _y = Integer.parseInt(y);
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("message", "Invalid inputs!");
            return "changeDestination";
        }
        System.out.println("change destination: " + "x: " + x + "y: " + y);
        List<Truck> trucks = truckDao.getTruckByTrackingID(trackingID);
        List<ShipInfo> shipInfos = trackingShipDao.getShipInfoByTrackingID(trackingID);
        ShipInfo shipInfo = shipInfos.get(0);
        String status = trucks.get(0).getStatus();
        if (status.equals("traveling") || status.equals("arrive warehouse")) {
            shipInfo.setDestX(_x);
            shipInfo.setDestY(_y);
            try {
                worldController.goDeliver(shipInfo);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "redirect:/userDetail";
        }
        model.addAttribute("message", "Can not change because the package is out of warehouse!");
        return "changeDestination";

    }

    @RequestMapping("/comment")
    public String toComment() {
        return "comment";
    }

    @PostMapping("/comment")
    public String comment(Model model, @RequestParam("comment") String comment, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long trackingID = (Long) session.getAttribute("currTrackingID");
        trackingShipDao.updateComment(trackingID, comment);
        User user = (User) session.getAttribute("user");
        if(user != null){
            String from = "shaoyf98@gmail.com";
            String to = user.getEmail();
            String subject = "Thank you for your comment!";
            String msg = "Dear " + user.getName() + ", your comment has been recorded!";
            mailController.sendEmail(from, to, subject, msg);
        }
        return "redirect:/userDetail";
    }
}
