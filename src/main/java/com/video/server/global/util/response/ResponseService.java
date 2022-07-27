package com.video.server.global.util.response;

import com.video.server.global.util.response.result.CommonResultResponse;
import com.video.server.global.util.response.result.ListResultResponse;
import com.video.server.global.util.response.result.SingleResultResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    @Getter
    @AllArgsConstructor
    enum response{
        SUCCESS(true, "성공하였습니다", 200);
        private boolean success;
        private String msg;
        private int status;
    }


    public ResponseEntity<CommonResultResponse> getSuccessResult(){
        return ResponseEntity.ok(getCommonResultResponse());
    }

    public <T> ResponseEntity<SingleResultResponse<T>> getSingleResult(T data){
        return ResponseEntity.ok(new SingleResultResponse<>(getCommonResultResponse(), data));
    }

    public <T> ResponseEntity<ListResultResponse<T>> getListResult(List<T> list){
        return ResponseEntity.ok(new ListResultResponse<>(getCommonResultResponse(), list));
    }

    private CommonResultResponse getCommonResultResponse() {
        return new CommonResultResponse(response.SUCCESS.isSuccess(), response.SUCCESS.getMsg(), response.SUCCESS.getStatus());
    }
}
