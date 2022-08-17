package com.skuniv.cgvr.controller;

import com.skuniv.cgvr.dto.notice.NormalNoticeResponseDto;
import com.skuniv.cgvr.service.notice.NormalNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final NormalNoticeService normalNoticeService;

    @GetMapping("/")
    public String index(Model model) {
        List<NormalNoticeResponseDto> normalNoticeResponseDtos = this.normalNoticeService.findAll().subList(0, 5);
        model.addAttribute("normalNoticeResponseDtos", normalNoticeResponseDtos);
        return "index";
    }

}
