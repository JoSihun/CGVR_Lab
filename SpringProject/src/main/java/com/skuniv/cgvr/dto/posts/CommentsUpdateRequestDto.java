package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Comments;
import lombok.Data;

@Data
public class CommentsUpdateRequestDto {
    private String content;

    public Comments toEntity() {
        return Comments.builder()
                .content(content)
                .build();
    }
}
