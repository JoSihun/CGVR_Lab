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
    public String notice_normal(Model model) {
        List<NormalNoticeResponseDto> normalNoticeResponseDtos = this.normalNoticeService.findAll();
        model.addAttribute("normalNoticeResponseDtos", normalNoticeResponseDtos);
        return "notice_normal";
    }

    @GetMapping("notice/post")
    public String notice_post() {
        return "notice_post_form";
    }

    @PostMapping("notice/post")
    public String notice_post_save(NormalNoticeSaveRequestDto requestDto) {
        Long id = normalNoticeService.save(requestDto);
        return "redirect:/notice/normal";
    }


}
