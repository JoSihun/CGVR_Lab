package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.Category;
import com.skuniv.cgvr.domain.Project;
import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Data;


@Data
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private Long hits;

    private String projectName;
    private String categoryName;


    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .hits(hits)
                .projectName(projectName)
                .categoryName(categoryName)
                .build();
    }
}
