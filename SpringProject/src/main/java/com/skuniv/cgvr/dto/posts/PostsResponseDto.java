package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostsResponseDto {
    private Long id;
    private Long hits;
    private String title;
    private String content;
    private String author;

    private String projectName;
    private String categoryName;

    private String createdDate;
    private String updatedDate;


    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.hits = entity.getHits();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

        this.projectName = entity.getProjectName();
        this.categoryName = entity.getCategoryName();

        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

}
