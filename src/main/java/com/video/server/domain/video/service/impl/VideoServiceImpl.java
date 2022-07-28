package com.video.server.domain.video.service.impl;

import com.video.server.domain.video.Video;
import com.video.server.domain.video.repository.VideoRepository;
import com.video.server.domain.video.service.VideoService;
import com.video.server.global.util.CurrentMemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private String defaultDir;
    private final VideoRepository videoRepository;
    private final CurrentMemberUtil currentMemberUtil;


    @Override
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

}
