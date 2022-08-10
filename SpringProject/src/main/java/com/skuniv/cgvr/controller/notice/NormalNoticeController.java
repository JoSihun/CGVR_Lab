package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.service.notice.NormalNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NormalNoticeController {
    private final NormalNoticeService normalNoticeService;

    @RequestMapping("/notice/normal")
    public String normal_notice(Model model) {
        List<NormalNotice> normalNoticeList = this.normalNoticeService.getList();
        model.addAttribute("normalNoticeList", normalNoticeList);
        return "notice_normal";
    }


    @RequestMapping("notice/post_form")
    public String post_form() {
        return "notice_post_form";
    }



}
