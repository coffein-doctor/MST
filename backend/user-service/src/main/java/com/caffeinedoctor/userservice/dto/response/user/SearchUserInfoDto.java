package com.caffeinedoctor.userservice.dto.response.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchUserInfoDto {

    private Long userId;
    private String nickname;
    private String profileImageUrl;
    private String introduction;

    @Builder
    public SearchUserInfoDto(Long userId, String nickname, String profileImageUrl, String introduction) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
    }

}
