package com.skuniv.cgvr.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
<<<<<<< HEAD:SpringProject/src/main/java/com/skuniv/cgvr/domain/Category.java
@NoArgsConstructor
=======
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
>>>>>>> YeomDongBin:SpringProject/src/main/java/com/skuniv/cgvr/domain/Tag.java
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @Builder
<<<<<<< HEAD:SpringProject/src/main/java/com/skuniv/cgvr/domain/Category.java
    public Category(Integer id, String content) {
=======
    public Category(Long id, String content) {
>>>>>>> YeomDongBin:SpringProject/src/main/java/com/skuniv/cgvr/domain/Tag.java
        this.id = id;
        this.content = content;
    }
}
