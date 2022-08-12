package com.skuniv.cgvr.repository.notice;

import com.skuniv.cgvr.domain.notice.LectureNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureNoticeRepository extends JpaRepository<LectureNotice, Long> {
    /*  왜인지 모르겠으나 JPQL쿼리로는 오류 발생
        생각에는 엔티티로 정의한 컬럼명과 테이블명은 카멜 표기법을 사용했으나
        MySQL에 저장될 때 스네이크 표기법으로 사용되어서 그런 것 같음
     */
    @Query(value = "SELECT * FROM lecture_notice ORDER BY mod_date DESC", nativeQuery = true)
    List<LectureNotice> findAllDesc();
}
