package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

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

	@PutMapping("/{id}")
	@PreAuthorize(SecurityConstant.USER)
	public ResponseEntity<?> update(@Valid @RequestBody MemberRequest memberRequest, @PathVariable("id") Long id){
		return new ResponseEntity<>(memberService.update(memberRequest, id), HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize(SecurityConstant.USER)
	public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody MemberRequest memberRequest){
		return new ResponseEntity<>(memberService.createMember(memberRequest), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(SecurityConstant.ADMIN)
	public ResponseEntity<Member> deleteMember(@PathVariable("id") Long id){
		return new ResponseEntity<>(memberService.deleteMember(id), HttpStatus.OK);
	}

}

