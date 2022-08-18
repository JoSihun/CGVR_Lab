package com.skuniv.cgvr.repository.notice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LaboratoryNoticeRepositoryTest {
//    @Autowired
//    LaboratoryNoticeRepository laboratoryNoticeRepository;
//
//    // 단위 테스트 종료 시 마다 수행되는 메소드를 지정함
//    @AfterEach
//    public void cleanup() {
//        laboratoryNoticeRepository.deleteAll();
//    }
//
//    @Test
//    public void 게시글저장_불러오기() {
//        // given
//        String title = "테스트 게시글";
//        String content = "테스트 본문";
//
//        // 테이블에 insert / update 수행
//        laboratoryNoticeRepository.save(LaboratoryNotice.builder()
//                .title(title)
//                .content(content)
//                .author("2016305078 최영환")
//                .category1_id(3L)
//                .build());
//
//        // when
//        List<LaboratoryNotice> laboratoryNoticeList = laboratoryNoticeRepository.findAll();
//
//        // then
//        LaboratoryNotice laboratoryNotice = laboratoryNoticeList.get(0);
//        assertThat(laboratoryNotice.getTitle()).isEqualTo(title);
//        assertThat(laboratoryNotice.getContent()).isEqualTo(content);
//    }
}
