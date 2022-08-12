package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.notice.AllNoticeDto;
import com.skuniv.cgvr.repository.notice.AllNoticeRepository;
import com.skuniv.cgvr.service.notice.AllNoticeService;
import com.skuniv.cgvr.service.notice.LaboratoryNoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class AllNoticeController {
    private AllNoticeService allNoticeService;
    @GetMapping("/notice/all")
    public String all_notice(Model model) {
        List<AllNoticeDto> noticeDtoList = allNoticeService.findAllDesc();
        model.addAttribute("all_notice_list", noticeDtoList);
        return "notice_all";
    }
}
