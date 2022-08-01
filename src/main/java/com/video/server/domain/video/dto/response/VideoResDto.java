package com.video.server.domain.video.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.video.server.domain.member.Member;
import lombok.*;

@Builder
@Setter
@AllArgsConstructor @NoArgsConstructor
public class VideoResDto {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private Member owner;

    @JsonProperty
    private String url;
}
