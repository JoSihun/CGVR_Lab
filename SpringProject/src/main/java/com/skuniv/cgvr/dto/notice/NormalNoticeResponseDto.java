package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import lombok.Getter;

@Getter
public class NormalNoticeResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public NormalNoticeResponseDto(NormalNotice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
