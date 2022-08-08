package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.General_Notice_Dto;
import com.skuniv.cgvr.dto.Lecture_Notice_Dto;
import com.skuniv.cgvr.service.General_Notice_Service;
import com.skuniv.cgvr.service.Lecture_Notice_Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class General_Notice_Controller {
    private General_Notice_Service general_notice_service;

    public General_Notice_Controller(General_Notice_Service general_notice_service) {
        this.general_notice_service = general_notice_service;
    }

    @GetMapping("/notice/normal")
    public String norNotice(Model model) {
        List<General_Notice_Dto> boardDtoList = general_notice_service.getList();
        model.addAttribute("general_notice_board", boardDtoList);
        return "notice/normalnotice";
    }

    /* Tmp Save Btn Page */
    @GetMapping("/notice/normal/save")
    public String write() {
        return "notice/tmpposts-save";
    }

    @PostMapping("/notice/normal/save")
    public String write(General_Notice_Dto Dto) {
        general_notice_service.savePost(Dto);
        return "redirect:/notice/normal";
    }
}
