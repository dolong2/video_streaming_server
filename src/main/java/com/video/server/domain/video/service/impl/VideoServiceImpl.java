package com.video.server.domain.video.service.impl;

import com.video.server.domain.video.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class VideoServiceImpl implements VideoService {

    private String defaultDir;


    @Override
    public void upload(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()){
            throw new RuntimeException();
        }
        String fullPath = defaultDir + multipartFile.getOriginalFilename();
        try{
            multipartFile.transferTo(new File(fullPath));
        }catch(IOException e){
            throw new RuntimeException();
        }
    }

}
