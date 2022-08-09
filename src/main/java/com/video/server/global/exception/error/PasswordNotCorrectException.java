package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordNotCorrectException extends BasicException{

    public PasswordNotCorrectException(){
        super(ErrorCode.PASSWORD_NOT_CORRECT);
    }
}
