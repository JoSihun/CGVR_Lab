package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.notice.LaboratoryNoticeResponseDto;
import com.skuniv.cgvr.dto.notice.LaboratoryNoticeSaveRequestDto;
import com.skuniv.cgvr.dto.notice.LaboratoryNoticeUpdateRequestDto;
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
    public String laboratoryNoticeBoard(Model model) {
        model.addAttribute("laboratory_notice_board", laboratoryNoticeService.findAllDesc());
        return "notice_laboratory";
    }

    // 글쓰기 페이지로 매핑
    @GetMapping("/notice/save")
    public String laboratoryNoticeSaveForm() {
        return "notice_post_form";
    }

    // 글 저장
    @PostMapping("/notice/save")
    public String laboratoryNoticeSave(LaboratoryNoticeSaveRequestDto requestDto) {
        Long id = laboratoryNoticeService.save(requestDto);

        return "redirect:/notice/laboratory/" + id.toString();
    }

    // 수정 화면
    @GetMapping("/notice/laboratory/update/{id}")
    public String laboratoryNoticeUpdateForm(@PathVariable Long id, Model model) {
        LaboratoryNoticeResponseDto dto = laboratoryNoticeService.findById(id);
        model.addAttribute("post", dto);
        return "notice_post_update";
    }

    // 수정
    @PostMapping("/notice/laboratory/update/{id}")
    public String laboratoryNoticeUpdate(@PathVariable Long id, LaboratoryNoticeUpdateRequestDto requestDto) {
        laboratoryNoticeService.update(id, requestDto);
        return "redirect:/notice/laboratory/" + id.toString();
    }

    // 상세 조회
    @GetMapping("/notice/laboratory/{id}")
    public String laboratoryNoticeView(@PathVariable Long id, Model model) {
        LaboratoryNoticeResponseDto dto = laboratoryNoticeService.findById(id);
        model.addAttribute("post", dto);
        return "notice_detail";
    }
}
