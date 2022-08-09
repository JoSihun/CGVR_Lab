package com.skuniv.cgvr.dto;

import com.skuniv.cgvr.domain.entity.Laboratory_Notice_Board;
import lombok.Builder;

import java.time.LocalDateTime;

public class Laboratory_Notice_Dto {
    private Integer id;
    private String title;
    private String content;
    private Integer hits;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
    private String author;
    private Integer tag1_id;
    private Integer tag2_id;
    private Integer tag3_id;
    private Integer attachment_id;

    public Laboratory_Notice_Board toEntity() {
        Laboratory_Notice_Board build = Laboratory_Notice_Board.builder()
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
    public Laboratory_Notice_Dto(Integer id, String title, String content, Integer hits, LocalDateTime reg_date, LocalDateTime mod_date, String author, Integer tag1_id, Integer tag2_id, Integer tag3_id, Integer attachment_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.reg_date = reg_date;
        this.mod_date = mod_date;
        this.author = author;
        this.tag1_id = tag1_id;
        this.tag2_id = tag2_id;
        this.tag3_id = tag3_id;
        this.attachment_id = attachment_id;
    }
}
