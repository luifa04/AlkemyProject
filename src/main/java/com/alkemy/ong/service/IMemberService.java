package com.alkemy.ong.service;

import java.util.List;
import java.util.Map;

import com.alkemy.ong.dto.MemberResponse;

public interface IMemberService {

	Map<String, Object> getAllMembers(Integer pageNo);

}
