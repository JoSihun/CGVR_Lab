package com.skuniv.cgvr.dto.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LectureNoticeUpdateRequestDto {
    private String title;
    private String content;
    private String author;

    private Long hits;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;


    @Builder
    public LectureNoticeUpdateRequestDto(String title, String content, String author, Long hits,
                                        Long category1_id, Long category2_id, Long category3_id,
                                        Long attachment_id) {
        this.title = title;
        this.content = content;
        this.author = author;

        this.hits = hits;
        this.category1_id = category1_id;
        this.category2_id = category2_id;
        this.category3_id = category3_id;
        this.attachment_id = attachment_id;

    }

    public LectureNotice toEntity() {
        return LectureNotice.builder()
                .title(title)
                .content(content)
                .author(author)
                .hits(hits)
                .category1_id(category1_id)
                .category2_id(category2_id)
                .category3_id(category3_id)
                .attachment_id(attachment_id)
                .build();
    }
}