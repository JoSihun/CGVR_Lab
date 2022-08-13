package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class NormalNoticeResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    private Long hits;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public NormalNoticeResponseDto(NormalNotice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

        this.hits = entity.getHits();
        this.category1_id = entity.getCategory1_id();
        this.category2_id = entity.getCategory2_id();
        this.category3_id = entity.getCategory3_id();
        this.attachment_id = entity.getAttachment_id();

        this.regDate = entity.getRegDate();
        this.modDate = entity.getModDate();
    }
}
