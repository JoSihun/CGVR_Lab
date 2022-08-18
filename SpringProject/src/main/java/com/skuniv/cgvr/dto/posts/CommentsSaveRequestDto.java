package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Comments;
import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Data;

@Data
public class CommentsSaveRequestDto {
    private String content;
    private String author;
    private Posts posts;


    public Comments toEntity() {
        return Comments.builder()
                .content(content)
                .author(author)
                .posts(posts)
                .build();
    }
}
