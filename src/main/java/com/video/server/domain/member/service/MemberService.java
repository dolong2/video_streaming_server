package com.video.server.domain.member.service;

import com.video.server.domain.member.dto.SignInMemberDto;
import com.video.server.domain.member.dto.SignUpMemberDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MemberService {
    Long join(SignUpMemberDto memberDto);
    Map<String, String> login(SignInMemberDto memberDto);
    void logout();
}
