package com.caffeinedoctor.userservice.dto.response.oauth2;

import com.caffeinedoctor.userservice.common.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2UserResponseDto oAuth2Response;

// 롬복의 @RequiredArgsConstructor 어노테이션을 사용하여 자동으로 생성자가 만들어집니다.
//    public CustomOAuth2User(OAuth2UserResponseDto userDto) {
//        this.oAuth2Response = oAuth2Response;
//    }


    @Override
    public Map<String, Object> getAttributes() {
        // OAuth2 인증 과정에서 사용되는 속성들을 가져오는 함수입니다.
        // 현재 구현에서는 null을 반환하고 있습니다.
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한 정보를 가져오는 함수입니다.
        // 현재 구현에서는 OAuth2UserResponseDto에서 권한 정보를 가져와서 반환합니다.
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                // OAuth2UserResponseDto에서 권한(role)을 가져옵니다.
                return oAuth2Response.getRole();
            }
        });
        return collection;
    }


    @Override
    public String getName() {
        // 사용자의 이름을 가져오는 함수입니다. "프로바이더_프로바이터Id 조합"을 사용자의 이름으로 사용합니다.
        return oAuth2Response.getUsername();
    }

//    public String getUsername() {
//        //
//        return oAuth2Response.getUsername();
//    }

    public String getEmail() {
        // 사용자의 이메일 주소를 가져오는 함수입니다.
        return oAuth2Response.getEmail();
    }

    public UserType getUserType() {
        // 사용자의 유형을 가져오는 함수입니다. 신규 or 기존
        return oAuth2Response.getUserType();
    }

    public String getProfileImageUrl() {
        // 사용자의 프로필 이미지 URL을 가져오는 함수입니다.
        return oAuth2Response.getProfileImageUrl();
    }
}