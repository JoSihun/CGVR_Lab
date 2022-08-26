package com.skuniv.cgvr.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String projectName;


    @Builder
    public Projects(String projectName) {
        this.projectName = projectName;
    }

    public void update(String projectName) {
        this.projectName = projectName;
    }
}
