package com.skuniv.cgvr.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NormalNoticeController {
    @GetMapping("/notice/normal")
    public String normal() {
        return "notice_normal";
    }
}
