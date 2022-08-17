package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    private final Long hits;
    private final Long attachment_id;

    private final String projectName;
    private final String categoryName;


    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

        this.hits = entity.getHits();
        this.projectName = entity.getProjectName();
        this.categoryName = entity.getCategoryName();
        this.attachment_id = entity.getAttachment_id();
    }
}
