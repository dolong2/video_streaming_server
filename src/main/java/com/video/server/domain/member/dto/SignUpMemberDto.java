package com.video.server.domain.member.dto;

import com.video.server.domain.member.Member;
import com.video.server.domain.member.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpMemberDto {

    private String email;
    private String password;
    private String name;

    public Member toEntity(String password){
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .roles(Collections.singletonList(Role.ROLE_MEMBER))
                .build();
    }
}
