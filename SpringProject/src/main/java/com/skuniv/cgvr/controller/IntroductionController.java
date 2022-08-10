package com.skuniv.cgvr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroductionController {
    @GetMapping("/introduction/professor")
    public String professor() {
        return "introduction_professor";
    }

    @GetMapping("/introduction/researchers")
    public String researchers() {
        return "introduction_researchers";
    }

    @GetMapping("/introduction/laboratory")
    public String laboratory() {
        return "introduction_laboratory";
    }
}
