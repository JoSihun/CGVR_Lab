package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Comments;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CommentsListResponseDto {
    private Long id;
    private String content;
    private String author;
    private String createdDate;
    private String updatedDate;

    // Posts ID가 왜 필요한지 모르겠음, 추후 재확인요망
    private Long postsId;


    public CommentsListResponseDto(Comments entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.postsId = entity.getPosts().getId();
    }
}
