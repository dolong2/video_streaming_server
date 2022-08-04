package com.video.server.domain.comment.service;

import com.video.server.domain.comment.dto.request.CommentReqDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    Long write(CommentReqDto commentReqDto);

    void delete(Long CommentIdx);

}
