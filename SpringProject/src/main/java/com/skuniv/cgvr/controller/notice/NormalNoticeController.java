package com.skuniv.cgvr.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NormalNotController {
    @GetMapping("/notice/normal")
    public String allNotice() {
        return "Notice/normalnotice";
    }
}
