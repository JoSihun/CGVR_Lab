package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.CategoryDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeAnswerResponseDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeResponseDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeSaveRequestDto;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.notice.LectureNoticeAnswerService;
import com.skuniv.cgvr.service.notice.LectureNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LectureNoticeController {
    private LectureNoticeService lectureNoticeService;
    private LectureNoticeAnswerService lectureNoticeAnswerService;
    private CategoryService categoryService;

    public LectureNoticeController(LectureNoticeService lectureNoticeService, LectureNoticeAnswerService lectureNoticeAnswerService, CategoryService categoryService) {
        this.lectureNoticeService = lectureNoticeService;
        this.lectureNoticeAnswerService = lectureNoticeAnswerService;
        this.categoryService = categoryService;
    }

    @GetMapping("/notice/lecture")
    public String notice_lecture(Model model) {
        List<LectureNoticeResponseDto> lectureNoticeResponseDtos = lectureNoticeService.findAll();
        for(LectureNoticeResponseDto lectureNoticeResponseDto : lectureNoticeResponseDtos) {
            // regDate 포맷변경
            lectureNoticeResponseDto.setRegDate();
            // 제목에 댓글 수 추가
            List<LectureNoticeAnswerResponseDto> lectureNoticeAnswerResponseDtos = lectureNoticeResponseDto.getLectureNoticeAnswerList();
            if(lectureNoticeAnswerResponseDtos.size() != 0)
            lectureNoticeResponseDto.setTitle(Integer.toString(lectureNoticeAnswerResponseDtos.size()));
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
        // 게시글 정보
        LectureNoticeResponseDto  lectureNoticeResponseDto = lectureNoticeService.findById(id);
        model.addAttribute("lectureNoticeResponseDto", lectureNoticeResponseDto);
        // 카테고리 정보
        CategoryDto categoryDto = categoryService.findById(lectureNoticeResponseDto.getCategory1_id());
        model.addAttribute("categoryDto", categoryDto);
        // 댓글 정보
        List<LectureNoticeAnswerResponseDto> lectureNoticeAnswerResponseDtos = lectureNoticeResponseDto.getLectureNoticeAnswerList();
        if(lectureNoticeAnswerResponseDtos != null && !lectureNoticeAnswerResponseDtos.isEmpty())
            model.addAttribute("lectureNoticeAnswerResponseDtos", lectureNoticeAnswerResponseDtos);
        // 댓글 수
        model.addAttribute("AnswerCount", lectureNoticeAnswerResponseDtos.size());
        return "notice_detail_form";
    }
}
