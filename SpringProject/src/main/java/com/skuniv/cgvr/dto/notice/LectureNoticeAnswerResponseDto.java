package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LectureNoticeAnswer;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class LectureNoticeAnswerResponseDto {
    private Long id;
    private String content;
    private String author;
    private String regDate;
    private String modDate;
    private Long lectureNoticeId;

    public LectureNoticeAnswerResponseDto(LectureNoticeAnswer entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.regDate = entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.modDate = entity.getModDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.lectureNoticeId = entity.getLectureNotice().getId();
    }
}
