package com.video.server.domain.video.service.impl;

import com.video.server.domain.video.Video;
import com.video.server.domain.video.repository.VideoRepository;
import com.video.server.domain.video.service.VideoService;
import com.video.server.global.util.CurrentMemberUtil;
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
    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

}
