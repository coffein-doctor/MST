package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.response.user.MypageDto;
import com.caffeinedoctor.userservice.dto.response.user.SearchUserInfoDto;
import com.caffeinedoctor.userservice.dto.response.user.UserDetailsDto;
import com.caffeinedoctor.userservice.dto.socialLoginDto;
import com.caffeinedoctor.userservice.dto.request.user.UserInfoRequestDto;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.enums.UserStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /** 소셜 회원 가입 **/
    Long socialLoginSignUp(socialLoginDto userDto);

    /** 이미지 프로필, 로그인 시간 업데이트 **/
    Long socialLogin(String userEmail, String newProfileImageUrl);

    /** 회원 정보 등록 및 수정 **/
    Long createUser(String username, UserInfoRequestDto userDto);
    UserDetailsDto updateUser(Long userId, String username, UserInfoRequestDto userDto);

    /** 회원 삭제 **/
    void hardDeleteUser(HttpServletRequest request, HttpServletResponse response, Long userId, String username);

    /** 회원 조회 **/
    UserDetailsDto getUserDetailsById(Long userId);
    User findUserByUsername(String username);
    User findUserById(Long userId);

    /** 마이페이지 조회 **/
    MypageDto getUserMypageInfo(Long userId);

    /** 회원 검색 **/
    SearchUserInfoDto searchUserByNickname(String nickname);

    /** 내가 팔로우하는 회원 목록 **/
    List<SearchUserInfoDto> getFollowingUsers(Long userId);

    /** 나를 팔로우하는 회원 목록 **/
    List<SearchUserInfoDto> getFollowerUsers(Long userId);

    /** 회원 Id 조회 **/
    Long getUserId(String username);

    /** 회원 찾기 **/
    boolean isUserExistsByEmail(String email);
    boolean isUserExistsByUsername(String username);

    /** 닉네임 찾기 **/
    boolean isNicknameExists(String nickname);

    /** 회원 가입 상태 **/
    UserStatus getUserStatusByUsername(String username);

}
