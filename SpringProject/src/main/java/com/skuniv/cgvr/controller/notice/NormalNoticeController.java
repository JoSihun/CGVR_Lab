package com.skuniv.cgvr.controller.notice;

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
    public String notice_normal() {
        return "notice_normal";
    }

    @GetMapping("notice/post")
    public String notice_post() {
        return "notice_post_form";
    }

    @RequestMapping("notice/post")
    public Long save(NormalNoticeSaveRequestDto requestDto) {
        return normalNoticeService.save(requestDto);
    }
}
