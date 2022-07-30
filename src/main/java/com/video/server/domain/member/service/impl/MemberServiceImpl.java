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
            throw new DuplicateMemberException("중복되는 회원 가입", ErrorCode.DUPLICATE_MEMBER);
        }
        String password = passwordEncoder.encode(memberDto.getPassword());
        Member member = memberDto.toEntity(password);
        return memberRepository.save(member).getId();
    }

    @Override
    @Transactional
    public Map<String, String> login(SignInMemberDto memberDto) {
        if(!memberRepository.existsByEmail(memberDto.getEmail())){
            throw new MemberNotFindException("회원을 찾을 수 없음", ErrorCode.MEMBER_NOT_FIND);
        }
        Member member = memberRepository.findOneByEmail(memberDto.getEmail())
                .orElseThrow(()-> new MemberNotFindException("회원을 찾을 수 없음", ErrorCode.MEMBER_NOT_FIND));
        if(!passwordEncoder.matches(memberDto.getPassword(), member.getPassword())){
            throw new PasswordNotCorrectException("비밀번호가 일치하지 않음", ErrorCode.PASSWORD_NOT_CORRECT);
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
            throw new TokenExpiredException("토큰이 만료되었습니다", ErrorCode.TOKEN_EXPIRED);
        }
        String email = tokenProvider.getUserEmail(refreshToken);
        String accessToken = tokenProvider.generateAccessToken(email);
        Map<String, String> result = new HashMap<>();
        result.put("accessToken", accessToken);
        return result;
    }
}
