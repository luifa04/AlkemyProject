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

import java.util.*;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements IMemberService{

	public String endpoint;
	public final Integer SIZE = 10;
	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;
	private final MessageSource messageSource;
	
	@Override
	public Map<String, Object> getAllMembers(Integer pageNo, String endPointName){

		String memberListIsEmpty = messageSource.getMessage("member.listEmpty", null, Locale.US);
		String memberLastPage = messageSource.getMessage("member.lastPage", null, Locale.US);
		final String membersName = "Members";
		final String nextPageName = "Next Page";
		final String previousPageName = "Previous Page";
		endpoint = endPointName;

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

		String nextPage = nextPageEndpoint(pageNo.intValue(),lastPage);
		String previousPage = previousPageEndpoint(pageNo.intValue());

		Map<String, Object> response = new HashMap<>();
		response.put(membersName, memberResponse);
		response.put(nextPageName, nextPage);
		response.put(previousPageName, previousPage);

		return response;
	}

	private String nextPageEndpoint(Integer actual, int lastPageNo){
		if (actual.intValue() >= lastPageNo){
			return null;
		}
		actual++;
		return endpoint.concat(actual.toString());
	}

	private String previousPageEndpoint(Integer actual){
		if (actual.intValue() == 0){
			return null;
		}
		actual--;
		return endpoint.concat(actual.toString());
	}

}
