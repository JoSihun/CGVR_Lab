package com.skuniv.cgvr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index_grid_test")
    public String index_grid_test() {
        return "index_grid_test";
    }
}
