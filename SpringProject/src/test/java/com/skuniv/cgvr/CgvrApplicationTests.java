package com.skuniv.cgvr;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.dto.user.UserFormDto;
import com.skuniv.cgvr.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
class CgvrApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;

	public User createUser() {
		UserFormDto userFormDto = UserFormDto.builder()
				.username("testid")
				.password("1234")
				.name("테스트")
				.email("test@test.com")
				.contact("010-1234-5678")
				.build();
		return User.createUser(userFormDto);
	}

	@Test
	@DisplayName("회원가입 테스트")
	void createUserTest() {
		User user = createUser();
		User createdUser = userService.createUser(user);
	}


	@Test
	void contextLoads() {

	}

}
