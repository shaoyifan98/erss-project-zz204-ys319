package edu.duke.erss.ups.Controller;

import edu.duke.erss.ups.AmazonController;
import edu.duke.erss.ups.WorldController;
import edu.duke.erss.ups.dao.UserDao;
import edu.duke.erss.ups.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user_test")
public class UserController {
    UserDao userDao;
    AmazonController amazonController;
    WorldController worldController;

    @Autowired
    UserController(UserDao userDao, AmazonController amazonController, WorldController worldController) {
        this.userDao = userDao;
        this.amazonController = amazonController;
        this.worldController = worldController;
    }

    @GetMapping("/")
    public String index() {
        return "Hello world";
    }

    @GetMapping("/allusers")
    public List<User> allUsers() {
        return userDao.getAllUser();
    }
}
