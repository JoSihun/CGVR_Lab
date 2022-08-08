package com.skuniv.cgvr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpRequest;

@Controller
public class UserController {
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
