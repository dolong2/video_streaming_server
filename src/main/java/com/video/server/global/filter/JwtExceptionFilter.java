package com.video.server.global.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.server.global.exception.ErrorCode;
import com.video.server.global.exception.error.BasicException;
import com.video.server.global.exception.error.TokenExpiredException;
import com.video.server.global.exception.error.TokenNotValidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        }catch(TokenExpiredException e){
            writeLog(request, response, e, e.getErrorCode());
        }catch(TokenNotValidException e){
            writeLog(request, response, e, e.getErrorCode());
        }
    }

    private void writeLog(HttpServletRequest request, HttpServletResponse response, BasicException e, ErrorCode errorCode) throws IOException {
        log.error(request.getRequestURI());
        log.error(errorCode.getMsg());
        e.printStackTrace();
        writeBody(response, e);
    }

    private void writeBody(HttpServletResponse response, BasicException e) throws IOException {
        String json = getJson(e);
        response.setStatus(e.getErrorCode().getStatus());
        response.setContentType("Application/json");
        response.getWriter().write(json);
    }

    private String getJson(BasicException e) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("success", e.getErrorCode().isSuccess());
        map.put("msg", e.getErrorCode().getMsg());
        map.put("status", e.getErrorCode().getStatus());
        String json = objectMapper.writeValueAsString(map);
        return json;
    }
}
