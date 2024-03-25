package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.request.user.SignUpUserRequestDto;

public interface UserService {
    /** 회원가입 **/
    void signUpUser (SignUpUserRequestDto signUpUserRequestDto);


}
