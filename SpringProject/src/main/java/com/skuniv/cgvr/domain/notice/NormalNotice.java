package com.skuniv.cgvr.domain.notice;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name="normal_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class NormalNotice extends TimeEntity{   // Auditing 기능 사용 Entity 상속
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
    private Integer tag1_id;
    @Column
    private Integer tag2_id;
    @Column
    private Integer tag3_id;
    @Column
    private Integer attachment_id;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
