package com.video.server.domain.video.service;

import com.video.server.domain.video.Video;
import com.video.server.domain.video.dto.response.VideoResDto;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface VideoService {
    void upload(MultipartFile multipartFile, String title);

    List<VideoResDto> getAllVideo();

    VideoResDto getOneVideo(Long videoId);

    ResourceRegion streaming(Long videoId, HttpHeaders headers) throws IOException;

    void deleteVideo(Long videoIdx);
}
