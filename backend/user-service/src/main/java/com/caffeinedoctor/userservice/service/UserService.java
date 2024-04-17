package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.response.user.UserDetailsDto;
import com.caffeinedoctor.userservice.dto.socialLoginDto;
import com.caffeinedoctor.userservice.dto.request.user.UserInfoRequestDto;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.enums.UserStatus;

import java.util.Optional;

public interface UserService {
    /** 소셜 회원 가입 **/
    Long socialLoginSignUp(socialLoginDto userDto);

    /** 이미지 프로필, 로그인 시간 업데이트 **/
    Long socialLogin(String userEmail, String newProfileImageUrl);

    /** 회원 정보 등록 및 수정 **/
    Long createUser(String username, UserInfoRequestDto userDto);
    UserDetailsDto updateUser(Long userId, String username, UserInfoRequestDto userDto);

    /** 회원 조회 **/
    UserDetailsDto getUserById(Long userId);
    User findUserByUsername(String username);

    /** 회원 찾기 **/
    boolean isUserExistsByEmail(String email);
    boolean isUserExistsByUsername(String username);

    /** 회원 가입 상태 **/
    UserStatus getUserStatusByUsername(String username);

}
