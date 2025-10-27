package com.test.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.rest.model.AddressDAO;
import com.test.rest.model.AddressDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

//@Controller
//@ResponseBody //2.

//REST API 전용 컨트롤러
@RestController //3.
@RequiredArgsConstructor
@Api(value = "주소록 컨트롤러", description = "주소록 데이터에 대한 REST API Controller 입니다.")
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
	
	@ApiOperation(value = "주소록 추가하기", notes = "주소록 데이터를 추가합니다.")
	@PostMapping("/address")
	public int add(
				@ApiParam (value = "주소록 데이터", required = true)
				@RequestBody AddressDTO dto
			) {
		
		//x-www-form-urlencoded > @RequestParam AddressDTO dto
		//JSON raw > @RequestBody AddressDTO dto
		
		System.out.println("dto: " + dto);
		
		return dao.add(dto);		
	}
	
	//목록보기
	//1. http://localhost:8080/rest/address
	//2. GET
	//3. return List<AddressDTO> > (변환, 매핑) > JSON
	@GetMapping("/address")
	public List<AddressDTO> list() {
		
		return dao.list();
	}
	
	
	//수정하기(U)
	//1. http://localhost:8080/rest/address
	//2. PUT > 모든 컬럼 수정
	//3. return int
	//@RequestMapping(value = "/address", method = RequestMethod.PUT)
	@PutMapping("/address/{seq}") //경로 변수(Path Variable)
	public int edit(@PathVariable("seq") String seq, 
					@RequestBody AddressDTO dto) {
		
		//- /address?seq=1
		//- /address/1
		dto.setSeq(seq);		
		
		return dao.edit(dto);
	}
	
	//삭제하기(D)
	//1. http://localhost:8080/rest/address
	//2. DELETE
	//3. return int
	@DeleteMapping("/address/{seq}")
	public int del(@PathVariable("seq") String seq) {
		
		return dao.del(seq);
	}
	
}





















