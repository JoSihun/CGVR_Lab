package com.skuniv.cgvr.domain.notice;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name="lecture_notice_answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureNoticeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String author;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @ManyToOne
    private LectureNotice lectureNotice;

    @Builder
    public LectureNoticeAnswer(String content, String author, LocalDateTime regDate, LocalDateTime modDate, LectureNotice lectureNotice) {
        this.content = content;
        this.author = author;
        this.regDate = LocalDateTime.now();
        this.modDate = LocalDateTime.now();
        this.lectureNotice = lectureNotice;
    }

    public void update(String content, String author) {
        this.content = content;
        this.author = author;
        this.modDate = LocalDateTime.now();
    }
}
