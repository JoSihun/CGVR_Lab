package com.skuniv.cgvr.domain.posts;

import com.skuniv.cgvr.domain.Attachments;
import com.skuniv.cgvr.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String author;

    @Column
    private String projectName;
    private String categoryName;

    @Column
    private Long hits;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    private List<Comments> commentsList;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    private List<Attachments> attachmenstList;


    @Builder
    public Posts(String title, String content, String author,
                 String projectName, String categoryName, Long hits,
                 List<Comments> commentsList, List<Attachments> attachmenstList) {
        this.title = title;
        this.content = content;
        this.author = author;

        this.hits = 0L;
        this.projectName = projectName;
        this.categoryName = categoryName;
        this.commentsList = commentsList;
        this.attachmenstList = attachmenstList;
    }

    public void increaseHits() {
        this.hits++;
    }

    public void update(String title, String content, Long attachment_id,
                       String projectName, String categoryName) {
        this.title = title;
        this.content = content;

        this.projectName = projectName;
        this.categoryName = categoryName;
    }
}
