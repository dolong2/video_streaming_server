package com.video.server.domain.video.controller;

import com.video.server.domain.video.dto.response.VideoResDto;
import com.video.server.domain.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/video")
public class VideoViewController {

    private final VideoService videoService;

    @GetMapping("/{videoId}")
    public String videoStreamView(@PathVariable Long videoId, Model model){
        VideoResDto video = videoService.getOneVideo(videoId);
        model.addAttribute("video", video);
        model.addAttribute("videoId", videoId);
        model.addAttribute("comments", video.getComments());
        return "video";
    }

}
