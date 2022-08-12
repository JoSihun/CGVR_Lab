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
    private Integer category1_id;
    private Integer category2_id;
    private Integer category3_id;
    private Integer attachment_id;
    public LectureNoticeDto(LectureNotice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.regDate = entity.getRegDate();
        this.modDate = entity.getModDate();
        this.author = entity.getAuthor();
        this.category1_id = entity.getCategory1_id();
        this.category2_id = entity.getCategory2_id();
        this.category3_id = entity.getCategory3_id();
        this.attachment_id = entity.getAttachment_id();
    }
    @Builder
    public LectureNoticeDto(Integer id, String title, String content, Integer hits, LocalDateTime regDate, LocalDateTime modDate, String author, Integer category1_id, Integer category2_id, Integer category3_id, Integer attachment_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.regDate = regDate;
        this.modDate = modDate;
        this.author = author;
        this.category1_id = category1_id;
        this.category2_id = category2_id;
        this.category3_id = category3_id;
        this.attachment_id = attachment_id;
    }
    public LectureNotice toEntity() {
        LectureNotice build = LectureNotice.builder()
                .id(id)
                .title(title)
                .content(content)
                .hits(hits)
                .author(author)
                .category1_id(category1_id)
                .category2_id(category2_id)
                .category3_id(category3_id)
                .attachment_id(attachment_id)
                .build();
        return build;
    }
}
