package com.skuniv.cgvr.domain.notice;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="lecture_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LectureNotice extends TimeEntity{   // Auditing 기능 사용 Entity 상속
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ColumnDefault("0")
    private Integer hits;
    @Column
    private String author;
    @Column(nullable = false)
    private Integer category1_id;
    @Column
    private Integer category2_id;
    @Column
    private Integer category3_id;
    @Column
    private Integer attachment_id;
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
