package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import com.skuniv.cgvr.domain.notice.LectureNoticeAnswer;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LectureNoticeResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    private Long hits;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;

    private String regDate;
    private String modDate;

    private List<LectureNoticeAnswerResponseDto> lectureNoticeAnswerList;

    public LectureNoticeResponseDto(LectureNotice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

        this.hits = entity.getHits();
        this.category1_id = entity.getCategory1_id();
        this.category2_id = entity.getCategory2_id();
        this.category3_id = entity.getCategory3_id();
        this.attachment_id = entity.getAttachment_id();

        this.regDate = entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.modDate = entity.getModDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));

        this.lectureNoticeAnswerList = entity.getLectureNoticeAnswerList().stream().map(LectureNoticeAnswerResponseDto::new).collect(Collectors.toList());
    }
    public void setRegDate() {
        // 공백을 기준으로 날짜 or 시간으로 분리
        String[] array = this.regDate.split(" ");
        // 오늘 올라온 게시글이라면
        if(array[0].equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))) {
            // 시간 정보만 표기
            this.regDate = array[1].substring(0, 5);
        }
        else {
            // 그 외엔 날짜 정보만 표기
            this.regDate = array[0];
        }
    }
    public void setTitle(String answerCount) {
        this.title = this.title + " [" + answerCount + "]";
    }
}