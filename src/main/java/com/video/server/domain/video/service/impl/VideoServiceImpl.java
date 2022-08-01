package com.video.server.domain.video.service.impl;

import com.video.server.domain.video.Video;
import com.video.server.domain.video.dto.response.VideoResDto;
import com.video.server.domain.video.repository.VideoRepository;
import com.video.server.domain.video.service.VideoService;
import com.video.server.global.util.CurrentMemberUtil;
import com.video.server.global.util.ResponseDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    @Value("${file.upload.location}")
    private String defaultDir;
    private final VideoRepository videoRepository;
    private final CurrentMemberUtil currentMemberUtil;


    @Override
    @Transactional
    public void upload(MultipartFile multipartFile, String title) {
        if(multipartFile.isEmpty()){
            throw new RuntimeException();
        }
        String fullPath = defaultDir + multipartFile.getOriginalFilename();
        fullPath=fullPath.replace(" ", "_");
        try{
            multipartFile.transferTo(new File(fullPath));
        }catch(IOException e){
            throw new RuntimeException();
        }
        Video video = Video.builder()
                .owner(currentMemberUtil.getCurrentMember())
                .title(title)
                .url(fullPath)
                .build();
        videoRepository.save(video);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideoResDto> getAllVideo() {
        List<Video> all = videoRepository.findAll();
        List<VideoResDto> videoResDto = ResponseDtoUtil.mapAll(all, VideoResDto.class);
        return videoResDto;
    }

    @Override
    @Transactional(readOnly = true)
    public VideoResDto getOneVideo(Long videoId){
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException());
        VideoResDto videoResDto = ResponseDtoUtil.mapping(video, VideoResDto.class);
        return videoResDto;
    }

}
