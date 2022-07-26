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

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ErrorResponse> DuplicateMemberExceptionHandler(HttpServletRequest request, DuplicateMemberException ex){
        printError(request, ex, ex.getErrorCode().getMsg());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().isSuccess(), ex.getErrorCode().getMsg(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> TokenExpiredExceptionHandler(HttpServletRequest request, TokenExpiredException ex){
        printError(request, ex, ex.getErrorCode().getMsg());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().isSuccess(), ex.getErrorCode().getMsg(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PasswordNotCorrectException.class)
    public ResponseEntity<ErrorResponse> PasswordNotCorrectExceptionHandler(HttpServletRequest request, PasswordNotCorrectException ex){
        printError(request, ex, ex.getErrorCode().getMsg());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().isSuccess(), ex.getErrorCode().getMsg(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MemberNotFindException.class)
    public ResponseEntity<ErrorResponse> MemberNotFindExceptionHandler(HttpServletRequest request, MemberNotFindException ex){
        printError(request, ex, ex.getErrorCode().getMsg());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().isSuccess(), ex.getErrorCode().getMsg(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<ErrorResponse> TokenNotValidExceptionHandler(HttpServletRequest request, TokenNotValidException ex){
        printError(request, ex, ex.getErrorCode().getMsg());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().isSuccess(), ex.getErrorCode().getMsg(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, RuntimeException ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }
}
