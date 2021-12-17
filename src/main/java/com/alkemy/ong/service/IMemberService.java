package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;

public interface IMemberService {

	List<MemberResponse> getAllMembers();

    MemberResponse update(MemberRequest memberRequest, Long id);
}
