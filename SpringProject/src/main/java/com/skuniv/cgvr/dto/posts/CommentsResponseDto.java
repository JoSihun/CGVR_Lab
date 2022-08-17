package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Comments;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CommentsResponseDto {
    private final String content;
    private final String author;
    private final String createdDate;
    private final String updatedDate;

    // Posts ID가 왜 필요한지 모르겠음, 추후 재확인요망
    private final Long postsId;


    public CommentsResponseDto(Comments entity) {
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.postsId = entity.getPosts().getId();
    }
}
