package com.skuniv.cgvr.repository.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LaboratoryNoticeRepository extends JpaRepository<LaboratoryNotice, Long> {
    @Query(value = "SELECT * FROM laboratory_notice ORDER BY mod_date DESC", nativeQuery = true)
    List<LaboratoryNotice> findAllDesc();
}
