package com.video.server.global.exception.handler;


import com.video.server.global.exception.ErrorResponse;
import com.video.server.global.exception.error.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BasicException.class)
    public ResponseEntity<ErrorResponse> DuplicateMemberExceptionHandler(HttpServletRequest request, BasicException ex){
        printError(request, ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().isSuccess(), ex.getErrorCode().getMsg(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, BasicException ex) {
        log.error(request.getRequestURI());
        log.error(ex.getErrorCode().getMsg());
        ex.printStackTrace();
    }
}
