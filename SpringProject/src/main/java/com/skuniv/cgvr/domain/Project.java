package com.skuniv.cgvr.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Project extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String projectName;


    @Builder
    public Project(String projectName) {
        this.projectName = projectName;
    }

    public void update(String projectName) {
        this.projectName = projectName;
    }
}
