package edu.duke.erss.ups.Controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.duke.erss.ups.dao.UserDao;
import edu.duke.erss.ups.entity.User;

@Controller
public class LoginController {
    UserDao userDao;

    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/login")
    public String index(Model model) {
        model.addAttribute("user", new User());
        // model.addAttribute("message", "hello");

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<User> users = userDao.getUserByName(user.getName());
        if (users.size() == 0) {
            model.addAttribute("user", new User());
            model.addAttribute("message", "Wrong user name!");
            return "login";
        } else if (!users.get(0).getPassword().equals(user.getPassword())) {
            model.addAttribute("user", new User());
            model.addAttribute("message", "Wrong password!");
            return "login";
        }
        session.setAttribute("user", users.get(0));
        return "redirect:/main";

    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute User user, Model model, HttpServletRequest request) {
        List<User> users = userDao.getUserByName(user.getName());
        if (users.size() != 0) {
            model.addAttribute("user", new User());
            model.addAttribute("message", "Username already existed!");
            return "register";
        }
        userDao.insertUser(user);
        return "redirect:/login";
    }
}
