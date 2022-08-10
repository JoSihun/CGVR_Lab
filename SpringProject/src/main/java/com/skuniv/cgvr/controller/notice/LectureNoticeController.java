package com.skuniv.cgvr.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LectureNoticeController {
    @GetMapping("/notice/lecture")
    public String lecture_notice() {
        return "notice_lecture";
    }
}
