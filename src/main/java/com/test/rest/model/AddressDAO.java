package com.test.rest.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AddressDAO {
	
	private final SqlSessionTemplate template;

	public AddressDTO m1() {

		return template.selectOne("rest.m1");
	}

	public int add(AddressDTO dto) {
		
		return template.insert("rest.add", dto);
	}

}










