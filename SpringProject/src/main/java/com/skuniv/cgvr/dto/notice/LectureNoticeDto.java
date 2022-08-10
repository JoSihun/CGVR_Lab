package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LectureNoticeDto {
    private Integer id;
    private String title;
    private String content;
    private Integer hits;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String author;
    private Integer tag1_id;
    private Integer tag2_id;
    private Integer tag3_id;
    private Integer attachment_id;
    public LectureNoticeDto(LectureNotice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.regDate = entity.getRegDate();
        this.modDate = entity.getModDate();
        this.author = entity.getAuthor();
        this.tag1_id = entity.getTag1_id();
        this.tag2_id = entity.getTag2_id();
        this.tag3_id = entity.getTag3_id();
        this.attachment_id = entity.getAttachment_id();
    }
    @Builder
    public LectureNoticeDto(Integer id, String title, String content, Integer hits, LocalDateTime regDate, LocalDateTime modDate, String author, Integer tag1_id, Integer tag2_id, Integer tag3_id, Integer attachment_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.regDate = regDate;
        this.modDate = modDate;
        this.author = author;
        this.tag1_id = tag1_id;
        this.tag2_id = tag2_id;
        this.tag3_id = tag3_id;
        this.attachment_id = attachment_id;
    }
    public LectureNotice toEntity() {
        LectureNotice build = LectureNotice.builder()
                .id(id)
                .title(title)
                .content(content)
                .hits(hits)
                .author(author)
                .tag1_id(tag1_id)
                .tag2_id(tag2_id)
                .tag3_id(tag3_id)
                .attachment_id(attachment_id)
                .build();
        return build;
    }
}
