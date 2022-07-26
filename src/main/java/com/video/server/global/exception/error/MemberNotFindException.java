package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFindException extends RuntimeException{
    private ErrorCode errorCode;

    public MemberNotFindException(String msg, ErrorCode errorCode){
        super(msg);
        this.errorCode=errorCode;
    }
}
