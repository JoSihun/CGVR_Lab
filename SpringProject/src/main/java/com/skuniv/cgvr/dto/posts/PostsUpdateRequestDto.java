package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Builder;
import lombok.Data;

@Data
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    private Long attachment_id;
    private String projectName;
    private String categoryName;


//    @Builder
//    public PostsUpdateRequestDto(String title, String content, Long attachment_id,
//                                 String projectName, String categoryName) {
//        this.title = title;
//        this.content = content;
//        this.projectName = projectName;
//        this.categoryName = categoryName;
//        this.attachment_id = attachment_id;
//    }


    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .projectName(projectName)
                .categoryName(categoryName)
                .attachment_id(attachment_id)
                .build();
    }
}
