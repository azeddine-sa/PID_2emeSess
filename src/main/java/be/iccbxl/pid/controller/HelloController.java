package be.iccbxl.pid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
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
