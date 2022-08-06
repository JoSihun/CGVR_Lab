package com.skuniv.cgvr.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LabNotController {
    @GetMapping("/notice/lab")
    public String allNotice() {
        return "Notice/labnotice";
    }
}
