package com.skuniv.cgvr.controller.notice;

import com.skuniv.cgvr.domain.notice.LaboratoryNotice;
import com.skuniv.cgvr.dto.notice.LaboratorySaveRequestDto;
import com.skuniv.cgvr.repository.notice.LaboratoryNoticeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LaboratoryNoticeControllerTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate;

    @Autowired
    private LaboratoryNoticeRepository laboratoryNoticeRepository;

    @AfterEach
    public void tearDown() throws Exception {
        laboratoryNoticeRepository.deleteAll();
    }

    @Test
    public void 게시글_등록() throws Exception {
        // given
        LaboratorySaveRequestDto requestDto = LaboratorySaveRequestDto.builder()
                .title("title")
                .content("content")
                .author("author")
                .category1_id(3L)
                .build();

        String url = "http://localhost:" + port + "/notice/save";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        // then
        List<LaboratoryNotice> all = laboratoryNoticeRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo("title");
        assertThat(all.get(0).getContent()).isEqualTo("content");
    }
}
