package com.test.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.rest.model.AddressDAO;
import com.test.rest.model.AddressDTO;

import lombok.RequiredArgsConstructor;

//@Controller
//@ResponseBody //2.

//REST API 전용 컨트롤러
@RestController //3.
@RequiredArgsConstructor
public class AddressController {
	
	private final AddressDAO dao;

//1.
//	@GetMapping("/m1.do")
//	public @ResponseBody AddressDTO m1() {
//		
//		return null;
//	}
	
	@GetMapping("/m1.do")
	public AddressDTO m1() {
		
		//AddressDTO > (변환) > JSON
		//1. 직접 문자열로 만들기
		//2. JSONObject(simple-json)
		//3. @ResponseBody(jackson-databind)
		
		AddressDTO dto = dao.m1();
		
		return dto;
	}
	
	
	//REST API
	//- tblAddress > CRUD
	
	//추가하기(C)
	//1. http://localhost:8080/rest/address
	//2. POST
	//3. return int
	@PostMapping("/address")
	public int add(AddressDTO dto) {
		
		System.out.println("dto: " + dto);
		
		return dao.add(dto);		
	}
	
	
}





















