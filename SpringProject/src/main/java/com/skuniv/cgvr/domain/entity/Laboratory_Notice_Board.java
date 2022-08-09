package com.skuniv.cgvr.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Laboratory_Notice_Board extends TimeEntity{    // Auditing 기능 사용 Entity 상속
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(length = 255)
    private String content;
    @ColumnDefault("0")
    private Integer hits;
    @Column
    private String author;
    @Column
    private Integer tag1_id;
    @Column
    private Integer tag2_id;
    @Column Integer tag3_id;
    @Column
    private Integer attachment_id;

    /* @AllArgsConstructor, @Builder 대체
    @Builder
    public Laboratory_Notice_Board(Integer id, String title, String content, Integer hits, LocalDateTime reg_date, LocalDateTime mod_date, String author, Integer tag1_id, Integer tag2_id, Integer tag3_id, Integer attachment_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.author = author;
        this.tag1_id = tag1_id;
        this.tag2_id = tag2_id;
        this.tag3_id = tag3_id;
        this.attachment_id = attachment_id;
    }
     */
}