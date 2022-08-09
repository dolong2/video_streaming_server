package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotEqualException extends BasicException{

    public MemberNotEqualException(){
        super(ErrorCode.MEMBER_NOT_EQUAL);
    }

}
