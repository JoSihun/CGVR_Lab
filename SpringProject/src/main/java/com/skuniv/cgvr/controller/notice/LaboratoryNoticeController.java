package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.notice.LaboratoryNoticeDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeDto;
import com.skuniv.cgvr.service.notice.LaboratoryNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LaboratoryNoticeController {
    private LaboratoryNoticeService laboratory_notice_service;

    public LaboratoryNoticeController(LaboratoryNoticeService laboratory_notice_service) {
        this.laboratory_notice_service = laboratory_notice_service;
    }

    @GetMapping("/notice/laboratory")
    public String labNotice(Model model) {
        List<LaboratoryNoticeDto> noticeDtoList = laboratory_notice_service.findAllDesc();
        model.addAttribute("laboratory_notice_list", noticeDtoList);
        return "notice_laboratory";
    }

    /* Tmp Save Btn Page */
    @GetMapping("/notice/laboratory/save")
    public String write() {
        return "tmpposts-save";
    }

    @PostMapping("/notice/laboratory/save")
    public String write(LaboratoryNoticeDto Dto) {
        laboratory_notice_service.savePost(Dto);
        return "redirect:/notice/laboratory";
    }
    /* detail tmp mapping */
    @GetMapping("/notice/laboratory/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        LaboratoryNoticeDto laboratoryNoticeDto = laboratory_notice_service.findById(id);
        model.addAttribute("post", laboratoryNoticeDto);
        return "notice_detail";
    }
}
