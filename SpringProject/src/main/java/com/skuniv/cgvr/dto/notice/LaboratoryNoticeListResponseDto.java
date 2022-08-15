package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LaboratoryNoticeListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modDate;
    private Long hits;

    public LaboratoryNoticeListResponseDto(LaboratoryNotice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modDate = entity.getModDate();
        this.hits = entity.getHits();
    }
}
