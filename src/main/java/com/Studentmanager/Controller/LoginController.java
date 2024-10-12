package com.Studentmanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/StudentManager")
public class LoginController {

    @GetMapping("/login.html")
    public String showLoginPage() {
        System.out.println("GET /StudentManager/login.html called"); // Để debug

        return "StudentManager/login";
    }

}
