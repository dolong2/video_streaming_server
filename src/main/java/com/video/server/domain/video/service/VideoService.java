package com.video.server.domain.video.service;

import com.video.server.domain.video.Video;
import com.video.server.domain.video.dto.response.VideoResDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface VideoService {
    void upload(MultipartFile multipartFile, String title);

    List<VideoResDto> getAllVideo();

    VideoResDto getOneVideo(Long videoId);
}
