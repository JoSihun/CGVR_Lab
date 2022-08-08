package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.Lecture_Notice_Dto;
import com.skuniv.cgvr.service.Lecture_Notice_Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Lecture_Notice_Controller {
    private Lecture_Notice_Service lecture_notice_service;

    public Lecture_Notice_Controller(Lecture_Notice_Service lecture_notice_service) {
        this.lecture_notice_service = lecture_notice_service;
    }

    @GetMapping("/notice/lect")
    public String lectNotice(Model model) {
        List<Lecture_Notice_Dto> boardDtoList = lecture_notice_service.getList();
        model.addAttribute("lecture_notice_board", boardDtoList);
        return "notice/lectnotice";
    }

    /* Tmp Save Btn Page */
    @GetMapping("/notice/lect/save")
    public String write() {
        return "notice/tmpposts-save";
    }

    @PostMapping("/notice/lect/save")
    public String write(Lecture_Notice_Dto Dto) {
        lecture_notice_service.savePost(Dto);
        return "redirect:/notice/lect";
    }
}
