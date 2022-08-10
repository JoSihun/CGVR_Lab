package com.skuniv.cgvr.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeAllController {
    @GetMapping("/notice/all")
    public String allNotice() {
        return "Notice/allnotice";
    }
}
