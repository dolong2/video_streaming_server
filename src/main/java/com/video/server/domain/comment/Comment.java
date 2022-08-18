package com.video.server.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.video.server.domain.member.Member;
import com.video.server.domain.video.Video;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "video")
    private Video video;
}
