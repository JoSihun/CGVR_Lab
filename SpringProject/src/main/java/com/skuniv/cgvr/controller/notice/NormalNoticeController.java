package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.notice.NormalNoticeDto;
import com.skuniv.cgvr.service.notice.NormalNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NormalNoticeController {
    private NormalNoticeService normal_notice_service;

    public NormalNoticeController(NormalNoticeService normal_notice_service) {
        this.normal_notice_service = normal_notice_service;
    }

    @GetMapping("/notice/normal")
    public String norNotice(Model model) {
        List<NormalNoticeDto> boardDtoList = normal_notice_service.getList();
        model.addAttribute("normal_notice_board", boardDtoList);
        return "notice_normal";
    }

    /* Tmp Save Btn Page */
    @GetMapping("/notice/normal/save")
    public String write() {
        return "tmpposts-save";
    }

    @PostMapping("/notice/normal/save")
    public String write(NormalNoticeDto Dto) {
        normal_notice_service.savePost(Dto);
        return "redirect:/notice/normal";
    }
}
