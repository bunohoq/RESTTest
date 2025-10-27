package com.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DBTests {

	//src/test/java > com.test.rest > DBTests.java
	@Autowired
	private SqlSessionTemplate template;
	
	@Test
	public void test() {
		
		assertNotNull(template);
		
		String time = template.selectOne("rest.time");
		
		System.out.println(time);
		
	}

}








