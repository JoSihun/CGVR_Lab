package com.skuniv.cgvr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 연구실 페이지
    @GetMapping("/lab")
    public String lab() {
        return "lab";
    }
}
