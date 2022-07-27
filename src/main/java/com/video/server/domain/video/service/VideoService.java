package com.video.server.domain.video.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface VideoService {
    void upload(MultipartFile multipartFile);
}
