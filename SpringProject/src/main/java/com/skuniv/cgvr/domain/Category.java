package com.skuniv.cgvr.domain;

import com.skuniv.cgvr.domain.posts.Posts;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Posts> postsList;


    @Builder
    public Category(String categoryName, List<Posts> postsList) {
        this.postsList = postsList;
        this.categoryName = categoryName;
    }

    public void update(String categoryName) {
        this.categoryName = categoryName;
    }
}
