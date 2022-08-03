package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FileNotFindException extends RuntimeException{
    private ErrorCode errorCode;

    public FileNotFindException(String msg, ErrorCode errorCode){
        super(msg);
        this.errorCode = errorCode;
    }
}
