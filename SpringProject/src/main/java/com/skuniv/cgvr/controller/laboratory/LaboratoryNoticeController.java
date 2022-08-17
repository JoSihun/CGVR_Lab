package com.skuniv.cgvr.controller.laboratory;

import org.springframework.stereotype.Controller;

@Controller
public class LaboratoryNoticeController {
    public String notice() {
        return "board_notice";
    }
}
