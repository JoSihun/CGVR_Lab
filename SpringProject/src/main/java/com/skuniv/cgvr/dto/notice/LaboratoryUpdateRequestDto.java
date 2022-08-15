package com.skuniv.cgvr.dto.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LaboratoryUpdateRequestDto {
    private String title;
    private String content;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;

    @Builder
    public LaboratoryUpdateRequestDto(String title, String content, Long category1_id,
                                      Long category2_id, Long category3_id, Long attachment_id) {
        this.title = title;
        this.content = content;
        this.category1_id = category1_id;
        this.category2_id = category2_id;
        this.category3_id = category3_id;
        this.attachment_id = attachment_id;
    }
}
