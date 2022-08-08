package com.skuniv.cgvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PreDestroy;

@EnableJpaAuditing
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

}
