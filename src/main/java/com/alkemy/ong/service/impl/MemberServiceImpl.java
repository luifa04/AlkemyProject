package com.alkemy.ong.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alkemy.ong.controller.MemberController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;
import com.alkemy.ong.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements IMemberService{

	public static final String ENDPOINT = "http://localhost:8080/members?page=";
	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;
	private final MessageSource messageSource;
	
	@Override
	public Map<String, Object> getAllMembers(Integer pageNo){

		Map<String, Object> response = new HashMap<>();
		String nextPage;
		String previousPage;
		String memberListIsEmpty = messageSource.getMessage("member.listEmpty", null, Locale.US);

		Pageable paging = PageRequest.of(pageNo, 10);
		Page<Member> pageMember = memberRepository.findAll(paging);
		int lastPage = pageMember.getTotalPages()-1;

		if(pageNo > lastPage){
			throw new IllegalArgumentException("El numero de pagina ingresado es erroneo, la ultima pagina es la " + lastPage);//TODO pasar a mensajes
		}

		List<MemberResponse> memberResponse = pageMember.map(memberMapper::memberModel2DTO).toList();

		if (memberResponse.isEmpty()){
			throw new EmptyDataException(memberListIsEmpty);
		}

		nextPage = nextPageEndpoint(pageNo.intValue(),lastPage);
		previousPage = previousPageEndpoint(pageNo.intValue());

		response.put("members", memberResponse);
		response.put("nextPage", nextPage);
		response.put("previousPage", previousPage);

		return response;
	}

	private String nextPageEndpoint(Integer actual, int lastPageNo){
		if (actual.intValue() >= lastPageNo){
			return null;
		}
		actual++;
		return ENDPOINT.concat(actual.toString());
	}

	private String previousPageEndpoint(Integer actual){
		if (actual.intValue() == 0){
			return null;
		}
		actual--;
		return ENDPOINT.concat(actual.toString());
	}


}
