package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.dto.notice.NormalNoticeResponseDto;
import com.skuniv.cgvr.dto.notice.NormalNoticeSaveRequestDto;
import com.skuniv.cgvr.service.notice.NormalNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NormalNoticeController {
    private final NormalNoticeService normalNoticeService;

    @GetMapping("notice/normal")
    public String normalNoticeBoard(Model model) {
        List<NormalNoticeResponseDto> normalNoticeResponseDtos = this.normalNoticeService.findAll();
        model.addAttribute("normalNoticeResponseDtos", normalNoticeResponseDtos);
        return "notice_normal";
    }

    @GetMapping("notice/normal/post/{id}")
    public String normalNoticePostView(@PathVariable Long id, Model model) {
        NormalNoticeResponseDto normalNoticeResponseDto = normalNoticeService.findById(id);
        model.addAttribute("normalNoticeResponseDto", normalNoticeResponseDto);
        return "notice_post_view";
    }


    @GetMapping("notice/post/form")
    public String normalNoticePostForm() {
        return "notice_post_form";
    }

    @PostMapping("notice/post/form")
    public String normalNoticePostSave(NormalNoticeSaveRequestDto requestDto) {
        Long id = normalNoticeService.save(requestDto);
        return "redirect:/notice/normal/post/id";
    }




}
