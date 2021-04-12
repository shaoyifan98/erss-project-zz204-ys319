package edu.duke.erss.ups.Controller;

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
    @Autowired
    UserDao userDao;

    @GetMapping("/")
    public String index() {
        return "Hello world";
    }

    @GetMapping("/allusers")
    public List<User> allUsers() {
        return userDao.getAllUser();
    }
}
