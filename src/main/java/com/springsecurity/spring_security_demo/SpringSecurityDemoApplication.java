package com.springsecurity.spring_security_demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com/springsecurity/spring_security_demo/Dao/*Mapper")
@RestController
public class SpringSecurityDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}
	@GetMapping("/me")
	public Map me(Authentication authentication) {
		System.err.println(authentication.getAuthorities().size());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ONE",authentication);
		return map;
	}
}
