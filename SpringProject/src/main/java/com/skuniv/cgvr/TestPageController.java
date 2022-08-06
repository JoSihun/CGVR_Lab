package com.skuniv.cgvr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPageController {

    @GetMapping("/testpage_index")
    public String testpage_index() {
        return "testpage_index";
    }

    @GetMapping("/testpage_professor")
    public String testpage_professor() { return "testpage_professor"; }
}
