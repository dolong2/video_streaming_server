package com.video.server.domain.comment.service.impl;

import com.video.server.domain.comment.Comment;
import com.video.server.domain.comment.dto.request.CommentReqDto;
import com.video.server.domain.comment.repository.CommentRepository;
import com.video.server.domain.comment.service.CommentService;
import com.video.server.domain.member.Member;
import com.video.server.domain.video.Video;
import com.video.server.domain.video.repository.VideoRepository;
import com.video.server.global.exception.ErrorCode;
import com.video.server.global.exception.error.FileNotFindException;
import com.video.server.global.util.CurrentMemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CurrentMemberUtil currentMemberUtil;
    private final VideoRepository videoRepository;

    @Override
    @Transactional
    public Long write(Long videoId, CommentReqDto commentReqDto) {
        Member member = currentMemberUtil.getCurrentMember();
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new FileNotFindException("비디오를 찾을수 없습니다", ErrorCode.FILE_NOT_FIND));
        Comment comment = commentReqDto.toEntity(video, member);
        video.getComments().add(comment);
        return commentRepository.save(comment).getId();
    }

    @Override
    @Transactional
    public void delete(Long commentIdx) {
        Member member = currentMemberUtil.getCurrentMember();
        Comment comment = commentRepository.findById(commentIdx)
                .orElseThrow(() -> new RuntimeException());
        if(comment.getMember() != member){
            throw new RuntimeException();
        }
        commentRepository.delete(comment);
    }
}
