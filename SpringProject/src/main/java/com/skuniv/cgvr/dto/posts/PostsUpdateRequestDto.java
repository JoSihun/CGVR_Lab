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
    private String projectName;
    private String categoryName;

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .projectName(projectName)
                .categoryName(categoryName)
                .build();
    }
}
