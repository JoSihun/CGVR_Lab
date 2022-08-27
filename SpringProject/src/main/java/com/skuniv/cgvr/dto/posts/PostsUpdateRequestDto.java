package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.Category;
import com.skuniv.cgvr.domain.Project;
import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Builder;
import lombok.Data;

@Data
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private Project project;
    private Category category;

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .project(project)
                .category(category)
                .build();
    }
}
