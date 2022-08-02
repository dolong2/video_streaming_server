package com.video.server.domain.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoViewController {

    @GetMapping("/{videoId}")
    public String videoStreamView(@PathVariable Long videoId, Model model){
        model.addAttribute("videoId", videoId);
        return "video";
    }

}
