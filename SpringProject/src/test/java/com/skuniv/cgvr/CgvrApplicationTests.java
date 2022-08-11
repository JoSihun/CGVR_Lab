package com.skuniv.cgvr;

import com.skuniv.cgvr.domain.notice.NormalNotice;
import com.skuniv.cgvr.repository.notice.NormalNoticeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;


@SpringBootTest
class CgvrApplicationTests {

	@Autowired
	private NormalNoticeRepository normalNoticeRepository;

	@Test
	void testJpa() {
		NormalNotice d1 = new NormalNotice();
		this.normalNoticeRepository.save(d1);
	}


	@Test
	void contextLoads() {

	}

}
