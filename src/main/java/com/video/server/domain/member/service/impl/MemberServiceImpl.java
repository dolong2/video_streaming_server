package com.video.server.domain.member.service.impl;

import com.video.server.domain.member.Member;
import com.video.server.domain.member.Repository.MemberRepository;
import com.video.server.domain.member.dto.SignInMemberDto;
import com.video.server.domain.member.dto.SignUpMemberDto;
import com.video.server.domain.member.service.MemberService;
import com.video.server.global.config.security.jwt.TokenProvider;
import com.video.server.global.exception.ErrorCode;
import com.video.server.global.exception.error.DuplicateMemberException;
import com.video.server.global.exception.error.MemberNotFindException;
import com.video.server.global.exception.error.PasswordNotCorrectException;
import com.video.server.global.exception.error.TokenExpiredException;
import com.video.server.global.util.CurrentMemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final CurrentMemberUtil currentMemberUtil;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public Long join(SignUpMemberDto memberDto) {
        if(memberRepository.existsByEmail(memberDto.getEmail())){
            throw new DuplicateMemberException();
        }
        String password = passwordEncoder.encode(memberDto.getPassword());
        Member member = memberDto.toEntity(password);
        return memberRepository.save(member).getId();
    }

    @Override
    @Transactional
    public Map<String, String> login(SignInMemberDto memberDto) {
        if(!memberRepository.existsByEmail(memberDto.getEmail())){
            throw new MemberNotFindException();
        }
        Member member = memberRepository.findOneByEmail(memberDto.getEmail())
                .orElseThrow(()-> new MemberNotFindException());
        if(!passwordEncoder.matches(memberDto.getPassword(), member.getPassword())){
            throw new PasswordNotCorrectException();
        }
        String accessToken = tokenProvider.generateAccessToken(memberDto.getEmail());
        String refreshToken = tokenProvider.generateRefreshToken(memberDto.getEmail());
        member.updateRefreshToken(refreshToken);
        Map<String, String> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("refreshToken", refreshToken);
        return result;
    }

    @Override
    @Transactional
    public void logout() {
        Member member = currentMemberUtil.getCurrentMember();
        member.updateRefreshToken(null);
    }

    @Override
    @Transactional
    public Map<String, String> refresh(String refreshToken){
        if(tokenProvider.isTokenExpired(refreshToken)){
            throw new TokenExpiredException();
        }
        String email = tokenProvider.getUserEmail(refreshToken);
        String accessToken = tokenProvider.generateAccessToken(email);
        Map<String, String> result = new HashMap<>();
        result.put("accessToken", accessToken);
        return result;
    }
}
