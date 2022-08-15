package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.notice.NormalNoticeDto;
import com.skuniv.cgvr.service.notice.NormalNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NormalNoticeController {
    private NormalNoticeService normal_notice_service;

    public NormalNoticeController(NormalNoticeService normal_notice_service) {
        this.normal_notice_service = normal_notice_service;
    }

    @GetMapping("/notice/normal")
    public String norNotice(Model model) {
        List<NormalNoticeDto> noticeDtoList = normal_notice_service.findAllDesc();
        model.addAttribute("normal_notice_list", noticeDtoList);
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
    @RequestMapping("notice/post_form")
    public String post_form() {
        return "notice_post_form";
    }

    /* detail tmp mapping */
    @GetMapping("/notice/normal/{id}")
    public String findById(@PathVariable Long id, Model model) {
        NormalNoticeDto normalNoticeDto = normal_notice_service.findById(id);
        model.addAttribute("post", normalNoticeDto);
        return "notice_detail";
    }
}
