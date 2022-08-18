package com.video.server.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.video.server.domain.video.Video;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "member")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class Member{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    private String email;

    private String name;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String refreshToken;

    @JsonIgnore
    @Enumerated(EnumType.STRING) @Column(name = "Role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "id"))
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "owner")
    List<Video> videos;

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

}
