package com.skuniv.cgvr.domain.notice;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class NormalNotice{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String author;

    @Column
    private Long hits;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private Long attachment_id;


    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder
    public NormalNotice(String title, String content, String author,
                        Long hits, Long attachment_id,
                        Long category1_id, Long category2_id, Long category3_id) {
        this.title = title;
        this.content = content;
        this.author = author;

        this.hits = Long.valueOf(0);
        this.category1_id = category1_id;
        this.category2_id = category2_id;
        this.category3_id = category3_id;
        this.attachment_id = attachment_id;

        this.regDate = LocalDateTime.now();
        this.modDate = LocalDateTime.now();
    }

    public void update(String title, String content, String author,
                       Long category1_id, Long category2_id, Long category3_id,
                       Long attachment_id) {
        this.title = title;
        this.content = content;
        this.author = author;

        this.category1_id = category1_id;
        this.category2_id = category2_id;
        this.category3_id = category3_id;
        this.attachment_id = attachment_id;
        this.modDate = LocalDateTime.now();
    }

    public void increaseHits() {
        this.hits++;
    }

}
