package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.dto.notice.NormalNoticeResponseDto;
import com.skuniv.cgvr.dto.notice.NormalNoticeSaveRequestDto;
import com.skuniv.cgvr.dto.notice.NormalNoticeUpdateRequestDto;
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

    /* 게시글 목록 */
    @GetMapping("notice/normal")
    public String normalNoticeBoard(Model model) {
        List<NormalNoticeResponseDto> normalNoticeResponseDtos = this.normalNoticeService.findAll();
        model.addAttribute("normalNoticeResponseDtos", normalNoticeResponseDtos);
        return "notice_normal";
    }

    /* 게시글 상세보기 */
    @GetMapping("notice/normal/post/{id}")
    public String normalNoticePostView(@PathVariable Long id, Model model) {
        NormalNoticeResponseDto normalNoticeResponseDto = normalNoticeService.findById(id);
        model.addAttribute("normalNoticeResponseDto", normalNoticeResponseDto);
        return "notice_post_view";
    }



    /* 게시글 작성폼 */
    @GetMapping("notice/post/form")
    public String normalNoticePostForm() {
        return "notice_post_form";
    }

    /* 게시글 작성요청 */
    @PostMapping("notice/post/form")
    public String normalNoticePostSave(NormalNoticeSaveRequestDto requestDto) {
        Long id = normalNoticeService.save(requestDto);
        return "redirect:/notice/normal/post/" + id;
    }



    /* 게시글 수정폼 */
    @GetMapping("notice/normal/post/update/{id}")
    public String normalNoticePostUpdate(@PathVariable Long id, Model model) {
        NormalNoticeResponseDto normalNoticeResponseDto = normalNoticeService.findById(id);
        model.addAttribute("normalNoticeResponseDto", normalNoticeResponseDto);
        return "notice_post_update_form";
    }

    /* 게시글 수정요청 / 추후 JavaScript를 통한 PutMapping 처리로 수정해야함 */
    @PostMapping("notice/normal/post/update/{id}")
    public String normalNoticePostUpdate(@PathVariable Long id, NormalNoticeUpdateRequestDto normalNoticeUpdateRequestDto) {
        normalNoticeService.update(id, normalNoticeUpdateRequestDto);
        return "redirect:/notice/normal/post/" + id;
    }



    /* 게시글 삭제 / 추후 javaScript를 통한 DeleteMapping 처리로 수정해야함 */
    @GetMapping("notice/normal/post/delete/{id}")
    public String normalNoticePostDelete(@PathVariable Long id) {
        System.out.println("Delete Check Point 1");
        normalNoticeService.delete(id);
        System.out.println("Delete Check Point 2");
        return "redirect:/notice/normal";
    }

}
