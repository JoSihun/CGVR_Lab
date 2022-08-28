package com.skuniv.cgvr.dto.posts;

import com.skuniv.cgvr.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private Long hits;
    private String title;
    private String author;

    private String projectName;
    private String categoryName;

    private String createdDate;
    private String updatedDate;

    private Long commentsSize;


    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.hits = entity.getHits();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.projectName = entity.getProjectName();
        this.categoryName = entity.getCategoryName();

        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.commentsSize = (long) entity.getCommentsList().size();
    }


    public void formatCreatedDate() {
        String[] array = this.createdDate.split(" ");   // 날짜 시간 분리
        // 최초작성일이 오늘인 경우
        if (array[0].equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))) {
            this.createdDate = array[1].substring(0, 5);      // 시간 분단위까지만 저장
        } else {
            this.createdDate = array[0];                      // 날짜 저장
        }
    }


    public void formatTitle(String commentsCount) {
        this.title += String.format(" [%s]", commentsCount);
    }
}
