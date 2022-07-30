package com.video.server.domain.member.controller;

import com.video.server.domain.member.service.MemberService;
import com.video.server.global.util.response.ResponseService;
import com.video.server.global.util.response.result.SingleResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class RefreshController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @PostMapping("/refresh")
    public ResponseEntity<SingleResultResponse<Map<String, String>>> tokenRefresh(@RequestHeader String refreshToken){
        return responseService.getSingleResult(memberService.refresh(refreshToken));
    }

}
