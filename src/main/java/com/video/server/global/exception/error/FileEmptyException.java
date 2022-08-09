package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FileEmptyException extends BasicException {

    public FileEmptyException(){
        super(ErrorCode.FILE_EMPTY);
    }
}
