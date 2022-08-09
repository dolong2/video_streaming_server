package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TokenNotValidException extends BasicException {

    public TokenNotValidException(){
        super(ErrorCode.TOKEN_NOT_VALID);
    }
}
