package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.notice.LectureNoticeDto;
import com.skuniv.cgvr.service.notice.LectureNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LectureNoticeController {
    private LectureNoticeService lecture_notice_service;

    public LectureNoticeController(LectureNoticeService lecture_notice_service) {
        this.lecture_notice_service = lecture_notice_service;
    }

    @GetMapping("/notice/lecture")
    public String lectNotice(Model model) {
        List<LectureNoticeDto> boardDtoList = lecture_notice_service.findAllDesc();
        model.addAttribute("lecture_notice_board", boardDtoList);
        return "notice_lecture";
    }

    /* Tmp Save Btn Page */
    @GetMapping("/notice/lecture/save")
    public String write() {
        return "tmpposts-save";
    }

    @PostMapping("/notice/lecture/save")
    public String write(LectureNoticeDto Dto) {
        lecture_notice_service.savePost(Dto);
        return "redirect:/notice/lecture";
    }
    /* detail tmp mapping */
    @GetMapping("/notice/lecture/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        LectureNoticeDto  lectureNoticeDto = lecture_notice_service.findById(id);
        model.addAttribute("post", lectureNoticeDto);
        return "notice_detail";
    }
}
