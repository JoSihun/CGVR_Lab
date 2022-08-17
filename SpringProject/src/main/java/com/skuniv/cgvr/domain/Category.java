package com.skuniv.cgvr.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @Builder
    public Category(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
