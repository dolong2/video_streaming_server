package com.video.server.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNKNOWN(false, "알 수 없는 에러", 500),
    MEMBER_NOT_FIND(false, "회원을 찾을수 없습니다.", 404),
    PASSWORD_NOT_CORRECT(false, "비밀번호가 올바르지 않습니다.", 403),
    DUPLICATE_MEMBER(false, "중복되는 이메일을 가지는 회원이 있습니다.", 400),
    TOKEN_EXPIRED(false, "토큰이 만료됐습니다.", 401),
    TOKEN_NOT_VALID(false, "토큰이 유효하지 않습니다.", 401),
    ;
    private boolean success;
    private String msg;
    private int status;
}
