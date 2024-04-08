package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.enums.UserType;
import com.caffeinedoctor.userservice.dto.request.user.UserRequestDto;
import com.caffeinedoctor.userservice.entitiy.User;

import java.util.Optional;

public interface UserService {
    /** 회원가입 **/
    Long signUp(UserRequestDto userDto);

    /** 회원조회 **/
    Optional<User> findOne(Long userId);

    /** 회원타입 **/
    UserType checkUserTypeByEmail(String email);

    /** 회원 찾기 **/
    boolean isUserExistsByEmail(String email);

    /** 이미지 프로필 업데이트 **/
    void updateProfileImage(String userEmail, String newProfileImageUrl);

    /** 로그인 시간 업데이트 **/
     void updateLoginDate(String userEmail);
}
