package com.skuniv.cgvr.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Lecture_Notice_Board extends TimeEntity{   // Auditing 기능 사용 Entity 상속
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
    private Integer tag_id;
    @Column
    private Integer attachment_id;

    /* @AllArgsConstructor, @Builder 대체
    @Builder
    public Lecture_Notice_Board(Integer id, String title, String content, Integer hits, LocalDateTime reg_date, LocalDateTime mod_date, String author, Integer tag_id, Integer attachment_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.author = author;
        this.tag_id = tag_id;
        this.attachment_id = attachment_id;
    }

     */
}
