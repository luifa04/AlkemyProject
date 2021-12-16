package com.alkemy.ong.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IMemberService;

import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final IMemberService memberService;
	
	@GetMapping
	@PreAuthorize(SecurityConstant.ADMIN)
	public ResponseEntity<Map<String, Object>> getAllMembers(@RequestParam(name = "page") Integer pageNo, HttpServletRequest request){
		String endPointName = request.getRequestURL() + "?" + request.getParameterNames().nextElement() + "=";
		Map<String, Object> listMembers = memberService.getAllMembers(pageNo, endPointName);
		return new ResponseEntity<>(listMembers, HttpStatus.OK);
	}
	
}
