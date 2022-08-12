package com.skuniv.cgvr.domain.notice;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="laboratory_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LaboratoryNotice extends TimeEntity{    // Auditing 기능 사용 Entity 상속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ColumnDefault("0")
    private Long hits;
    @Column
    private String author;
    @Column(nullable = false)
    private Long category1_id;
    @Column
    private Long category2_id;
    @Column
    private Long category3_id;
    @Column
    private Long attachment_id;
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}