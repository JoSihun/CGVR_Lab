package com.skuniv.cgvr.domain.posts;

import com.skuniv.cgvr.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comments extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String author;

    @ManyToOne
    private Posts posts;


    @Builder
    public Comments(String content, String author, Posts posts) {
        this.content = content;
        this.author = author;
        this.posts = posts;
    }


    public void update(String content) {
        this.content = content;
    }
}
