package com.skuniv.cgvr.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
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
public class Lecture_Notice_Board extends TimeEntity{
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    private String content;
    @Column
    private Integer hits;
    @Column
    private String author;
    @Column
    private Integer tag_id;
    @Column
    private Integer attachment_id;

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
}
