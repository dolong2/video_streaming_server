package com.video.server.domain.video.controller;

import com.video.server.domain.video.dto.response.VideoResDto;
import com.video.server.domain.video.service.VideoService;
import com.video.server.global.util.response.ResponseService;
import com.video.server.global.util.response.result.CommonResultResponse;
import com.video.server.global.util.response.result.ListResultResponse;
import com.video.server.global.util.response.result.SingleResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping("/stream/{videoId}")
    public ResponseEntity<ResourceRegion> streaming(@PathVariable Long videoId, @RequestHeader HttpHeaders httpHeaders) throws IOException {
        ResourceRegion region = videoService.streaming(videoId, httpHeaders);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(region);
    }

    @DeleteMapping("/{videoIdx}")
    public ResponseEntity<CommonResultResponse> delete(@PathVariable Long videoIdx){
        videoService.deleteVideo(videoIdx);
        return responseService.getSuccessResult();
    }
}
