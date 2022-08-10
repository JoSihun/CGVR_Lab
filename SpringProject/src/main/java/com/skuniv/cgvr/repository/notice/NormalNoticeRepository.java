package com.skuniv.cgvr.repository.notice;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NormalNoticeRepository extends JpaRepository<NormalNotice, Integer> {
    NormalNotice findByTitle(String title);
    NormalNotice findByTitleOrContent(String title, String Content);
    NormalNotice findByTitleAndContent(String title, String Content);
    List<NormalNotice> findByTitleLike(String title);
}
