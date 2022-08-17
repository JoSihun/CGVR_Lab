package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import com.skuniv.cgvr.domain.notice.LectureNoticeAnswer;
import lombok.Data;

@Data
public class LectureNoticeAnswerSaveRequestDto {
    private Long id;
    private String content;
    private String author;
    private String regDate;
    private String modDate;
    private LectureNotice lectureNotice;

    public LectureNoticeAnswer toEntity() {
        return LectureNoticeAnswer.builder()
                .content(content)
                .author(author)
                .lectureNotice(lectureNotice)
                .build();
    }
}
