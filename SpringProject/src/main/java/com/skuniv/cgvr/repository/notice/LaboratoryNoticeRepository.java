package com.skuniv.cgvr.repository.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryNoticeRepository extends JpaRepository<LaboratoryNotice, Integer> {
}
