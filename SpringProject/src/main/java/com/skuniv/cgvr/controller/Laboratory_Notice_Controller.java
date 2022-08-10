package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.Laboratory_Notice_Dto;
import com.skuniv.cgvr.service.Laboratory_Notice_Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Laboratory_Notice_Controller {
    private Laboratory_Notice_Service laboratory_notice_service;

    public Laboratory_Notice_Controller(Laboratory_Notice_Service laboratory_notice_service) {
        this.laboratory_notice_service = laboratory_notice_service;
    }

    @GetMapping("/notice/lab")
    public String labNotice(Model model) {
        List<Laboratory_Notice_Dto> boardDtoList = laboratory_notice_service.getList();
        model.addAttribute("laboratory_notice_board", boardDtoList);
        return "notice_laboratory";
    }

    /* Tmp Save Btn Page */
    @GetMapping("/notice/lab/save")
    public String write() {
        return "tmpposts-save";
    }

    @PostMapping("/notice/lab/save")
    public String write(Laboratory_Notice_Dto Dto) {
        laboratory_notice_service.savePost(Dto);
        return "redirect:/notice/lect";
    }
}
