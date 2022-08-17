package com.skuniv.cgvr.dto.notice;


import com.skuniv.cgvr.domain.notice.LectureNotice;
import lombok.*;


//@NoArgsConstructor
//@Getter
@Data
public class LectureNoticeSaveRequestDto {
    private String title;
    private String content;
    private String author;

    private Long hits;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;

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
