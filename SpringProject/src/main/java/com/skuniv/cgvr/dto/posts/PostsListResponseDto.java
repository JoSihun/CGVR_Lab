package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private Long hits;
    private String title;
    private String author;

    private String createdDate;
    private String updatedDate;


    public PostsListResponseDto(Posts entity) {
        this.id = getId();
        this.hits = getHits();
        this.title = getTitle();
        this.author = getAuthor();

        this.createdDate = getCreatedDate();
        this.updatedDate = getUpdatedDate();
    }
}
