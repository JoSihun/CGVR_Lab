package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    private Long hits;
    private Long attachment_id;

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
