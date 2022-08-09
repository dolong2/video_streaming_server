package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FileNotFindException extends BasicException{

    public FileNotFindException(){
        super(ErrorCode.FILE_NOT_FIND);
    }
}
