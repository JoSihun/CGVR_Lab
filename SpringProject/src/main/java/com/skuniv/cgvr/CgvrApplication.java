package com.skuniv.cgvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@EnableJpaAuditing		// JPA Auditing 기능 사용을 위함
@SpringBootApplication
public class CgvrApplication {

	/* SSH 터널링
	private static SSHConnection conn;
	public CgvrApplication() {
		conn = new SSHConnection().openSSH( res -> {
			if(!res) {
				System.out.println("포트포워딩 실패!");
				System.exit(0);
			}
		});
	}

	 */
	public static void main(String[] args) {
		SpringApplication.run(CgvrApplication.class, args);
	}

	/* SSH 연결 종료
	@PreDestroy
	public void end() {
		try {
			conn.closeSSH();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */

	/* 페이징 테스트 */
	/* Pageable 객체 커스터 마이징 */
	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customizer() {
		return pageableResolver -> {
			pageableResolver.setOneIndexedParameters(true);
		};
	}

}
