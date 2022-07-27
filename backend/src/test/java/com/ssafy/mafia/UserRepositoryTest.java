package com.ssafy.mafia;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mafia.entity.User;
import com.ssafy.mafia.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired 
	UserRepository userRepository;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUser() throws Exception{
		User user = new User();
		user.setUser_name("남한솔");
		user.setEmail("hansol620915@naver.com");
		user.setId("hansol");
		user.setImage_path("temp");
		user.setMy_point(0);
		user.setPassword("1234");
		user.setNickname("솔123");
		//when
		int savedId = userRepository.save(user);
		
		
	}

}
