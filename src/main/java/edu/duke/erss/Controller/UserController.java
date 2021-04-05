package edu.duke.erss.Controller;

import edu.duke.erss.dao.UserDao;
import edu.duke.erss.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user_test")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/index")
    public String index() {
        return "Hello world";
    }

    @GetMapping("/allusers")
    public List<User> allUsers() {
        return userDao.getAllUser();
    }
}
