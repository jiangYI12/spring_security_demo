package com.springsecurity.spring_security_demo;

import com.springsecurity.spring_security_demo.Dao.UsersMapper;
import com.springsecurity.spring_security_demo.Entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityDemoApplicationTests {

	@Autowired
	RedisTemplate<String,String> redisTemplate;
	@Autowired
	UsersMapper usersMapper;
	@Test
	public void contextLoads() {
//        redisTemplate.opsForValue().set("my","11111122");
//		sendSms.sendMsg("13202361544","527609");
		Users users = new Users();
		users.setUserName("1213");
		users.setPhone("12312312");
		users.setAccount("sdasd");
		usersMapper.selectByPrimaryKeySelective(users);
        System.out.println(redisTemplate.opsForValue().get("my"));
	}

}
