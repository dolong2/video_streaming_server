package com.video.server.global.exception.error;


import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateMemberException extends BasicException{

    public DuplicateMemberException(){
        super(ErrorCode.DUPLICATE_MEMBER);
    }
}
