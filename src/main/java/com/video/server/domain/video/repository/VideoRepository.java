package com.video.server.domain.video.repository;

import com.video.server.domain.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
