package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.notice.LaboratoryResponseDto;
import com.skuniv.cgvr.dto.notice.LaboratorySaveRequestDto;
import com.skuniv.cgvr.service.notice.LaboratoryNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller     // Controller 와 RestController 의 분리가 필요함
public class LaboratoryNoticeController {
    private final LaboratoryNoticeService laboratoryNoticeService;

    @GetMapping("/notice/laboratory")
    public String labNotice(Model model) {
        model.addAttribute("laboratory_notice_board", laboratoryNoticeService.findAllDesc());
        return "notice_laboratory";
    }

    // 글쓰기 페이지로 매핑
    @GetMapping("/notice/save")
    public String save() {
        //return "notice_post_form";
        return "tmp_laboratory_save_form";
    }

    // 글 저장
    @PostMapping("/notice/save")
    public String save(LaboratorySaveRequestDto requestDto) {
        Long id = laboratoryNoticeService.save(requestDto);
        return "redirect:/notice/laboratory";
    }

    // 수정
    // 수정 페이지가 필요함

    // 상세 조회
    @GetMapping("/notice/laboratory/{id}")
    public String findById(@PathVariable Long id, Model model) {
        LaboratoryResponseDto dto = laboratoryNoticeService.findById(id);
        model.addAttribute("post", dto);
        return "notice_detail";
    }
}
