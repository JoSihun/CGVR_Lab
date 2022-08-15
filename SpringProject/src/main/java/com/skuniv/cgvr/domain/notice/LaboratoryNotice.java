package com.skuniv.cgvr.domain.notice;

import com.skuniv.cgvr.domain.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="laboratory_notice")
public class LaboratoryNotice extends BaseTimeEntity {    // Auditing 기능 사용 Entity 상속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // PK, auto increment
    private Long id;
    @Column(length = 500)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private Long hits;
    private String author;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;

    @Builder
    public LaboratoryNotice(String title, String content, String author, Long hits,
                            Long category1_id, Long category2_id,
                            Long category3_id, Long attachment_id) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.hits = 0L;
        this.category1_id = category1_id;
        this.category2_id = category2_id;
        this.category3_id = category3_id;
        this.attachment_id = attachment_id;
    }
}