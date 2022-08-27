package com.skuniv.cgvr.domain;

import com.skuniv.cgvr.domain.posts.Posts;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Project extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String projectName;

    @OneToMany(mappedBy = "project")
    private List<Posts> postsList;


    @Builder
    public Project(String projectName, List<Posts> postsList) {
        this.postsList = postsList;
        this.projectName = projectName;
    }

    public void update(String projectName) {
        this.projectName = projectName;
    }
}
