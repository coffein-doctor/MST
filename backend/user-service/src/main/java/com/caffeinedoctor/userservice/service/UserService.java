package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.request.user.UserRequestDto;
import com.caffeinedoctor.userservice.entitiy.User;

import java.util.Optional;

public interface UserService {
    /** 회원가입 **/
    Long createUser(UserRequestDto userDto);

    /** 회원조회 **/
    Optional<User> findOne(Long userId);
}
