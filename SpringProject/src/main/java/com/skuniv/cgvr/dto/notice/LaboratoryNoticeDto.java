package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import lombok.Builder;

import java.time.LocalDateTime;

public class LaboratoryNoticeDto {
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

    public LaboratoryNotice toEntity() {
        LaboratoryNotice build = LaboratoryNotice.builder()
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

    @Builder
    public LaboratoryNoticeDto(Integer id, String title, String content, Integer hits, LocalDateTime regDate, LocalDateTime modDate, String author, Integer tag1_id, Integer tag2_id, Integer tag3_id, Integer attachment_id) {
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
}
