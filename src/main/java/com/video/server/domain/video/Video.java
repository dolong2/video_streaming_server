package com.video.server.domain.video;

import com.video.server.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String url;//실제 영상이 저장된 경로
    @ManyToOne
    @JoinColumn(name = "owner")
    private Member owner;
}
