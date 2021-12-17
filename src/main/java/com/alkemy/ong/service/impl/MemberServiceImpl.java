package com.alkemy.ong.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.util.UpdateFields;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;
import com.alkemy.ong.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements IMemberService{

	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;
	private final MessageSource messageSource;
	private final UpdateFields updateFields;

	@Override
	public List<MemberResponse> getAllMembers(){
		String memberListIsEmpty = messageSource.getMessage("member.listEmpty", null, Locale.US);
		List<Member> listMember = memberRepository.findAll();
		List<MemberResponse> listMemberResponse = new ArrayList<>();
		listMember.stream().forEach(member -> {
			MemberResponse memberResponse = memberMapper.memberModel2DTO(member);
			listMemberResponse.add(memberResponse);
		});
        if(listMemberResponse.isEmpty()){
            throw new EmptyDataException(memberListIsEmpty);
        }		
		return listMemberResponse;
	}

	@Transactional
	@Override
	public MemberResponse update(MemberRequest memberRequest, Long id){
		String memberNotFound = messageSource.getMessage("member.notFound", null, Locale.US);

		Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundException(memberNotFound));

		updateFields.updateIfNotBlankAndNotEqual(memberRequest.getName(), member.getName(), member::setName, "name");
		updateFields.updateIfNotEmptyAndNotEqual(memberRequest.getFacebookUrl(), member.getFacebookUrl(), member::setFacebookUrl, "facebookUrl");
		updateFields.updateIfNotEmptyAndNotEqual(memberRequest.getInstagramUrl(), member.getInstagramUrl(), member::setInstagramUrl, "instagramUrl");
		updateFields.updateIfNotEmptyAndNotEqual(memberRequest.getLinkedinUrl(), member.getLinkedinUrl(), member::setLinkedinUrl, "linkedinUrl");
		updateFields.updateIfNotBlankAndNotEqual(memberRequest.getImage(), member.getImage(), member::setImage, "image");
		updateFields.updateIfNotBlankAndNotEqual(memberRequest.getDescription(), member.getDescription(), member::setDescription, "description");

		if(updateFields.isHasUpdate()){
			member.setDateUpdate(LocalDateTime.now());
		}

		return new MemberResponse(member.getName(),
								  	member.getFacebookUrl(),
									member.getInstagramUrl(),
									member.getLinkedinUrl(),
									member.getImage(),
									member.getDescription());
	}
	
}
