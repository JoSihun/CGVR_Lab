package com.skuniv.cgvr.dto.notice;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LaboratoryNoticeUpdateRequestDto {
    private String title;
    private String content;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;

    @Builder
    public LaboratoryNoticeUpdateRequestDto(String title, String content, Long category1_id,
                                            Long category2_id, Long category3_id, Long attachment_id) {
        this.title = title;
        this.content = content;
        this.category1_id = category1_id;
        this.category2_id = category2_id;
        this.category3_id = category3_id;
        this.attachment_id = attachment_id;
    }
}
