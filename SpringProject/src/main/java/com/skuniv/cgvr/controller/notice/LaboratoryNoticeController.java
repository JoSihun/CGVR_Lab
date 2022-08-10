package com.skuniv.cgvr.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LaboratoryNoticeController {
    @GetMapping("/notice/laboratory")
    public String laboratory_notice() {
        return "notice_laboratory";
    }
}
