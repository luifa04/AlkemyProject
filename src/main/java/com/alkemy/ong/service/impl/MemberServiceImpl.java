package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements IMemberService{

	public final String ENDPOINT = "http://localhost:8080/members?page=";
	public final Integer SIZE = 10;
	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;
	private final MessageSource messageSource;
	
	@Override
	public Map<String, Object> getAllMembers(Integer pageNo){

		Map<String, Object> response = new HashMap<>();
		String nextPage;
		String previousPage;
		String memberListIsEmpty = messageSource.getMessage("member.listEmpty", null, Locale.US);
		String memberLastPage = messageSource.getMessage("member.lastPage", null, Locale.US);

		Pageable paging = PageRequest.of(pageNo, SIZE);
		Page<Member> pageMember = memberRepository.findAll(paging);
		int lastPage = pageMember.getTotalPages()-1;

		if(pageNo > lastPage){
			throw new IllegalArgumentException(memberLastPage + lastPage);
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
