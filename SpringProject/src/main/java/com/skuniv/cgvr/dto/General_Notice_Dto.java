package com.skuniv.cgvr.dto;


import com.skuniv.cgvr.domain.entity.General_Notice_Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class General_Notice_Dto {
    private Integer id;
    private String title;
    private String content;
    private Integer hits;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
    private String author;
    private Integer tag_id;
    private Integer attachment_id;

    public General_Notice_Board toEntity() {
        General_Notice_Board build = General_Notice_Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .hits(hits)
                .author(author)
                .tag_id(tag_id)
                .attachment_id(attachment_id)
                .build();
        return build;
    }

    @Builder
    public General_Notice_Dto(Integer id, String title, String content, Integer hits, LocalDateTime reg_date, LocalDateTime mod_date, String author, Integer tag_id, Integer attachment_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.reg_date = reg_date;
        this.mod_date = mod_date;
        this.author = author;
        this.tag_id = tag_id;
        this.attachment_id = attachment_id;
    }
}

