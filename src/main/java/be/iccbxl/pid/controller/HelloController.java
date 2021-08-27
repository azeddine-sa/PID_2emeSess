package be.iccbxl.pid.controller;

import java.time.LocalDateTime;
import java.util.*;
import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import be.iccbxl.pid.model.User;


@Controller
public class HelloController {

    private static List<User> users = new ArrayList<User>();

    @GetMapping("/")
    public String index(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "user/register";
    }

       

    @GetMapping("/admin")
    public String admin() {
        return "Hi Admin!";
    }
    
    @GetMapping("/member")
    public String member() {
        return "Hi Member!";
    }

}
