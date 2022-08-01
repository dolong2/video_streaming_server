package com.video.server.domain.video.controller;

import com.video.server.domain.video.dto.response.VideoResDto;
import com.video.server.domain.video.service.VideoService;
import com.video.server.global.util.response.ResponseService;
import com.video.server.global.util.response.result.CommonResultResponse;
import com.video.server.global.util.response.result.ListResultResponse;
import com.video.server.global.util.response.result.SingleResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/video")
public class VideoController {

    private final VideoService videoService;
    private final ResponseService responseService;

    @PostMapping
    public ResponseEntity<CommonResultResponse> uploadVideo(@RequestParam String title, @RequestParam MultipartFile video){
        videoService.upload(video, title);
        return responseService.getSuccessResult();
    }

    @GetMapping()
    public ResponseEntity<ListResultResponse<VideoResDto>> getAllVideo(){
        return responseService.getListResult(videoService.getAllVideo());
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<SingleResultResponse<VideoResDto>> getOneVideo(@PathVariable Long videoId){
        VideoResDto video = videoService.getOneVideo(videoId);
        return responseService.getSingleResult(video);
    }
}
