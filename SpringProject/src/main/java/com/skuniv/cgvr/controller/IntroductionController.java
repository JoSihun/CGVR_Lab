package com.skuniv.cgvr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroductionController {
    @GetMapping("/introduction/professor")
    public String professor() {
        return "professor";
    }

    @GetMapping("/introduction/researchers")
    public String researchers() {
        return "researchers";
    }

    @GetMapping("/introduction/laboratory")
    public String laboratory() {
        return "laboratory";
    }
}
