package com.skuniv.cgvr.repository.notice;

import com.skuniv.cgvr.dto.notice.AllNoticeDto;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface AllNoticeRepository {
    @Query(value = "SELECT * FROM lecture_notice UNION ALL SELECT * FROM laboratory_notice UNION ALL SELECT * FROM normal_notice ORDER BY reg_date DESC", nativeQuery = true)
    List<AllNoticeDto> findAllDesc();
}
