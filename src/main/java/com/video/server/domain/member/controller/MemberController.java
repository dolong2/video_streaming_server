package com.video.server.domain.member.controller;

import com.video.server.domain.member.dto.SignInMemberDto;
import com.video.server.domain.member.dto.SignUpMemberDto;
import com.video.server.domain.member.service.MemberService;
import com.video.server.global.util.response.ResponseService;
import com.video.server.global.util.response.result.CommonResultResponse;
import com.video.server.global.util.response.result.SingleResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;

    @PostMapping("/join")
    public ResponseEntity<CommonResultResponse> join(@RequestBody SignUpMemberDto signUpMemberDto){
        memberService.join(signUpMemberDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/login")
    public ResponseEntity<SingleResultResponse<Map<String, String>>> login(@RequestBody SignInMemberDto signInMemberDto){
        Map<String, String> result = memberService.login(signInMemberDto);
        return responseService.getSingleResult(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<CommonResultResponse> logout(){
        memberService.logout();
        return responseService.getSuccessResult();
    }
}
