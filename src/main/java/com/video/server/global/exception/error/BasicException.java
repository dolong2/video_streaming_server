package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BasicException extends RuntimeException{
    private ErrorCode errorCode;

    public BasicException(ErrorCode errorCode){
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }
}
