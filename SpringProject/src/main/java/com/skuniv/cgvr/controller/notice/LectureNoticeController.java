package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.CategoryDto;
import com.skuniv.cgvr.dto.notice.LectureNoticeDto;
import com.skuniv.cgvr.service.CategoryService;
import com.skuniv.cgvr.service.notice.LectureNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LectureNoticeController {
    private LectureNoticeService lecture_notice_service;
    private CategoryService categoryService;

    public LectureNoticeController(LectureNoticeService lecture_notice_service, CategoryService categoryService) {
        this.lecture_notice_service = lecture_notice_service;
        this.categoryService = categoryService;
    }

    @GetMapping("/notice/lecture")
    public String lectNotice(Model model) {
        /*
        //  기존에 받아오던 LectureNoticeDtoList
        //  List<LectureNoticeDto> noticeDtoList = lecture_notice_service.findAllDesc();
         */
        // 날짜 포매팅 후 받아오는 LectureNoticeHashMapList
        List<HashMap> noticeMapList = lecture_notice_service.findAllDescAsMap();
        //model.addAttribute("lecture_notice_list", noticeDtoList);
        model.addAttribute("notice_map_list", noticeMapList);
        return "notice_lecture";
    }

    /* Post Btn Page */
    @GetMapping("/notice/lecture/save")
    /*
    public String write() {
        return "tmpposts-save";
    }
     */
    public String write() { return "notice_post_form"; }

    @PostMapping("/notice/lecture/save")
    public String write(LectureNoticeDto lectureNoticeDto) {
        lecture_notice_service.savePost(lectureNoticeDto);
        return "redirect:/notice/lecture";
    }
    /* detail tmp mapping */
    @GetMapping("/notice/lecture/{id}")
    public String findById(@PathVariable Long id, Model model) {
        LectureNoticeDto  lectureNoticeDto = lecture_notice_service.findById(id);
        // 카테고리 id로 카테고리 Dto 얻기
        CategoryDto categoryDto = categoryService.findById(lectureNoticeDto.getCategory1_id());
        // LocalDateTime 포맷 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm");
        // 변환된 LocalDateTime 따로 Attribute
        model.addAttribute("regDate", lectureNoticeDto.getRegDate().format(formatter));
        model.addAttribute("lecture_notice_dto", lectureNoticeDto);
        model.addAttribute("category_dto", categoryDto);
        return "notice_detail_form";
    }
}
