package com.video.server.domain.comment.dto.request;

import com.video.server.domain.comment.Comment;
import com.video.server.domain.member.Member;
import com.video.server.domain.video.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class CommentReqDto {

    private String content;

    public Comment toEntity(Video video, Member member){
        return Comment.builder()
                .content(content)
                .video(video)
                .member(member)
                .build();
    }

}
