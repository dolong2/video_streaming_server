package com.video.server.domain.video.service.impl;

import com.video.server.domain.video.Video;
import com.video.server.domain.video.dto.response.VideoResDto;
import com.video.server.domain.video.repository.VideoRepository;
import com.video.server.domain.video.service.VideoService;
import com.video.server.global.exception.ErrorCode;
import com.video.server.global.exception.error.FileNotFindException;
import com.video.server.global.util.CurrentMemberUtil;
import com.video.server.global.util.ResponseDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
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

    @Value("${file.stream.location}")
    private String streamLocation;

    private final VideoRepository videoRepository;
    private final CurrentMemberUtil currentMemberUtil;


    @Override
    @Transactional
    public void upload(MultipartFile multipartFile, String title) {
        if(multipartFile.isEmpty()){
            throw new FileNotFindException();
        }
        String fullPath = defaultDir + multipartFile.getOriginalFilename();
        fullPath=fullPath.replace(" ", "_");
        try{
            multipartFile.transferTo(new File(fullPath));
        }catch(IOException e){
            throw new RuntimeException("몰?루");
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
                .orElseThrow(() -> new FileNotFindException());
        VideoResDto videoResDto = ResponseDtoUtil.mapping(video, VideoResDto.class);
        return videoResDto;
    }

    @Override
    public ResourceRegion streaming(Long videoId, HttpHeaders headers) throws IOException {
        String videoUrl = videoRepository.findById(videoId)
                .orElseThrow(() -> new FileNotFindException()).getUrl();
        videoUrl=videoUrl.substring(80);
        UrlResource video = new UrlResource("classpath:" + streamLocation + "/" + videoUrl);
        ResourceRegion region = resourceRegion(video, headers);
        return region;
    }

    private ResourceRegion resourceRegion(UrlResource video, HttpHeaders headers) throws IOException{
        final Long chunkSize = 1000000L;
        Long contentLength = video.contentLength();
        HttpRange httpRange = headers.getRange().stream().findFirst().get();
        if(httpRange!=null){
            Long start = httpRange.getRangeStart(contentLength);
            Long end = httpRange.getRangeEnd(contentLength);
            Long rangeLength = Long.min(chunkSize, end - start + 1);
            return new ResourceRegion(video, start, rangeLength);
        }else {
            long rangeLength = Long.min(chunkSize, contentLength);
            return new ResourceRegion(video, 0, rangeLength);
        }
    }
}
