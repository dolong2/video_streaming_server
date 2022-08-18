package com.video.server.domain.video;

import com.video.server.domain.comment.Comment;
import com.video.server.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String url;//실제 영상이 저장된 경로
    @ManyToOne
    @JoinColumn(name = "owner")
    private Member owner;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "video")
    private List<Comment> comments;
}
