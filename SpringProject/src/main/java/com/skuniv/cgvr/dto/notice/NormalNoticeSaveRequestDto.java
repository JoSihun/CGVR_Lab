package com.skuniv.cgvr.dto.notice;


import com.skuniv.cgvr.domain.notice.NormalNotice;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class NormalNoticeSaveRequestDto {
    private String title;
    private String content;
    private String author;

    private Integer hits;

    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;

    private LocalDateTime regDate;
    private LocalDateTime modDate;


    @Builder
    public NormalNoticeSaveRequestDto(String title, String content, String author,
                                      Long category1_id, Long category2_id, Long category3_id,
                                      Long attachment_id) {
        this.title = title;
        this.content = content;
        this.author = author;

        this.category1_id = category1_id;
        this.category2_id = category2_id;
        this.category3_id = category3_id;
        this.attachment_id = attachment_id;
    }

    public NormalNotice toEntity() {
        return NormalNotice.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

