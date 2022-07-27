package com.video.server.global.util.response.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResultResponse {
    private boolean success;
    private String msg;
    private int status;
}
