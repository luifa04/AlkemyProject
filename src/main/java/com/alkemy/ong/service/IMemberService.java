package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;

import javax.validation.Valid;
import java.util.Map;

public interface IMemberService {

	Map<String, Object> getAllMembers(Integer pageNo, String endPointName);

    MemberResponse update(MemberRequest memberRequest, Long id);

    MemberResponse createMember(@Valid MemberRequest memberRequest);

}

