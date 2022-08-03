package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FileEmptyException extends RuntimeException{
    private ErrorCode errorCode;

    public FileEmptyException(String msg, ErrorCode errorCode){
        super(msg);
        this.errorCode = errorCode;
    }
}
