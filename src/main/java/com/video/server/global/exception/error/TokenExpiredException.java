package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TokenExpiredException extends BasicException{

    public TokenExpiredException(){
        super(ErrorCode.TOKEN_EXPIRED);
    }
}
