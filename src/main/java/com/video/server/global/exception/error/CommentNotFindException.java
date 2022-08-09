package com.video.server.global.exception.error;

import com.video.server.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CommentNotFindException extends BasicException{

    public CommentNotFindException(){
        super(ErrorCode.COMMENT_NOT_FIND);
    }
}
