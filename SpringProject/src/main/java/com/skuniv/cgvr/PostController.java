package com.skuniv.cgvr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("/post_form")
    public String post_form() {
        return "post_form";
    }
}
