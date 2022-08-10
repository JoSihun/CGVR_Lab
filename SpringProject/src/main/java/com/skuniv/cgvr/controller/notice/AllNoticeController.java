package com.skuniv.cgvr.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllNoticeController {
    @GetMapping("/notice/all")
    public String allNotice() {
        return "Notice/allnotice";
    }
}
