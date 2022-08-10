package com.skuniv.cgvr.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LectNotController {
    @GetMapping("/notice/lect")
    public String allNotice() {
        return "Notice/lectnotice";
    }
}
