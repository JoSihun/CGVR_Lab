package com.skuniv.cgvr.repository.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import com.skuniv.cgvr.domain.notice.NormalNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NormalNoticeRepository extends JpaRepository<NormalNotice, Long> {
    NormalNotice findByTitle(String title);
    NormalNotice findByTitleAndContent(String title, String content);
    NormalNotice findByTitleOrContent(String title, String content);
    NormalNotice findByTitleOrderByRegDateAsc(String title);
    NormalNotice findByTitleOrderByRegDateDesc(String title);
    List<NormalNotice> findByTitleLike(String title);
}
