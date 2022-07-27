package com.video.server.global.util.response.result;

import lombok.Getter;

import java.util.List;

@Getter
public class ListResultResponse<T> extends CommonResultResponse{

    List<T> list;

    public ListResultResponse(CommonResultResponse commonResultResponse, List<T> list) {
        super(commonResultResponse.isSuccess(), commonResultResponse.getMsg(), commonResultResponse.getStatus());
        this.list = list;
    }
}
