package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import com.skuniv.cgvr.domain.notice.LectureNotice;
import com.skuniv.cgvr.domain.notice.NormalNotice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AllNoticeDto {
    private Long id;
    private String title;
    private String content;
    private Long hits;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String author;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;

    public AllNoticeDto(Long id, String title, String content, Long hits, LocalDateTime regDate, LocalDateTime modDate, String author, Long category1_id, Long category2_id, Long category3_id, Long attachment_id) {
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

    public AllNoticeDto(AllNoticeDto entity) {
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
}
