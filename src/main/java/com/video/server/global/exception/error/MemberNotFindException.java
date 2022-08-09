package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFindException extends BasicException{

    public MemberNotFindException(){
        super(ErrorCode.MEMBER_NOT_FIND);
    }
}
