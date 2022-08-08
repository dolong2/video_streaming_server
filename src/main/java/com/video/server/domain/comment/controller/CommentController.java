package com.video.server.domain.comment.controller;

import com.video.server.domain.comment.dto.request.CommentReqDto;
import com.video.server.domain.comment.service.CommentService;
import com.video.server.global.util.response.ResponseService;
import com.video.server.global.util.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
public class CommentController {

    private final ResponseService responseService;
    private final CommentService commentService;

    @PostMapping("/{videoIdx}")
    public ResponseEntity<CommonResultResponse> writeComment(@PathVariable Long videoIdx, @RequestBody CommentReqDto commentReqDto){
        commentService.write(videoIdx, commentReqDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{commentIdx}")
    public ResponseEntity<CommonResultResponse> deleteComment(@PathVariable Long commentIdx){
        commentService.delete(commentIdx);
        return responseService.getSuccessResult();
    }
}
