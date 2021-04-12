package edu.duke.erss.ups.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellwController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
