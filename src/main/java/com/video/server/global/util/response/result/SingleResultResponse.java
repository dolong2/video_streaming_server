package com.video.server.global.util.response.result;

import lombok.Getter;

@Getter
public class SingleResultResponse<T> extends CommonResultResponse{

    T data;

    public SingleResultResponse(CommonResultResponse commonResultResponse, T data) {
        super(commonResultResponse.isSuccess(), commonResultResponse.getMsg(), commonResultResponse.getStatus());
        this.data = data;
    }
}
