package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LectureNoticeSaveRequestDto {
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

    public LectureNotice toEntity() {
        LectureNotice build = LectureNotice.builder()
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
