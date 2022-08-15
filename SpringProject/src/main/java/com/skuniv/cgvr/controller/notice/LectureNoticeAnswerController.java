package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.dto.notice.LectureNoticeAnswerSaveRequestDto;
import com.skuniv.cgvr.service.notice.LectureNoticeAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class LectureNoticeAnswerController {
    private final LectureNoticeAnswerService lectureNoticeAnswerService;
    @PostMapping("/notice/lecture/{id}/answer")
    public String save_answer(@PathVariable Long id, LectureNoticeAnswerSaveRequestDto lectureNoticeAnswerSaveRequestDto) {
        lectureNoticeAnswerService.save(id, lectureNoticeAnswerSaveRequestDto);
        return String.format("redirect:/notice/lecture/%s", id.toString());
    }
}
