package com.caffeinedoctor.userservice.dto.response.oauth2;

import com.caffeinedoctor.userservice.common.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2UserResponseDto userDto;

//    public CustomOAuth2User(OAuth2UserResponseDto userDto) {
//        this.userDto = userDto;
//    }


    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return userDto.getRole();
            }
        });
        return collection;
    }


    @Override
    public String getName() {
        return null;
    }

    public String getUsername() {
        return userDto.getUsername();
    }

    public String getEmail() {
        return userDto.getEmail();
    }

    public UserType getUserType() {
        return userDto.getUserType();
    }

    public String getProfileImgeUrl() {
        return userDto.getProfileImageUrl();
    }
}