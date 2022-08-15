package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import com.skuniv.cgvr.dto.CategoryDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeResponseDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeSaveRequestDto;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.notice.LectureNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LectureNoticeController {
    private LectureNoticeService lectureNoticeService;
    private CategoryService categoryService;

    public LectureNoticeController(LectureNoticeService lectureNoticeService, CategoryService categoryService) {
        this.lectureNoticeService = lectureNoticeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/notice/lecture")
    public String notice_lecture(Model model) {
        List<LectureNoticeResponseDto> lectureNoticeResponseDtos = lectureNoticeService.findAll();
        for(LectureNoticeResponseDto lectureNoticeResponseDto : lectureNoticeResponseDtos) {
            // regDate 포맷변경
            lectureNoticeResponseDto.setRegDate();
        }
        model.addAttribute("lectureNoticeResponseDtos", lectureNoticeResponseDtos);
        return "notice_lecture";
    }

    @GetMapping("/notice/post")
    public String save() { return "notice_post_form"; }

    @PostMapping("/notice/post")
    public String save_post(LectureNoticeSaveRequestDto lectureNoticeSaveRequestDto) {
        lectureNoticeService.save(lectureNoticeSaveRequestDto);
        return "redirect:/notice/lecture";
    }

    @GetMapping("/notice/lecture/{id}")
    public String findById(@PathVariable Long id, Model model) {
        LectureNoticeResponseDto  lectureNoticeResponseDto = lectureNoticeService.findById(id);
        // 카테고리 id로 카테고리 Dto 얻기
        CategoryDto categoryDto = categoryService.findById(lectureNoticeResponseDto.getCategory1_id());

        model.addAttribute("lectureNoticeResponseDto", lectureNoticeResponseDto);
        model.addAttribute("categoryDto", categoryDto);
        return "notice_detail_form";
    }
}
