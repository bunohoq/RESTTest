package com.test.rest.model;

import java.util.List;

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

	public List<AddressDTO> list() {
		
		return template.selectList("rest.list");
	}

	public int edit(AddressDTO dto) {
		
		return template.update("rest.edit", dto);
	}

	public int del(String seq) {
		
		return template.delete("rest.del", seq);
	}

}










