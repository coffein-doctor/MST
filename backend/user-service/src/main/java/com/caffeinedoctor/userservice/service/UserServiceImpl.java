package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.socialLoginDto;
import com.caffeinedoctor.userservice.dto.request.user.UserInfoRequestDto;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.enums.UserStatus;
import com.caffeinedoctor.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true) // (성능 최적화 - 읽기 전용에만 사용)
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    @Autowired // 생성자 주입
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    /** 신규 회원의 소셜 로그인을 통한 회원 가입 **/
    @Override
    @Transactional // 기본: false (true면 데이터 변경이 안된다.)
    public Long socialLoginSignUp(@Valid socialLoginDto userDto) {
        // 중복 회원 검증
        log.info(String.valueOf(userDto));

        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .profileImageUrl(userDto.getProfileImageUrl())
                .build();

        userRepository.save(user);

        return user.getId();
    }

    /** 기존 회원의 소셜 로그인을 통한 프로필 이미지, 로그인 시간 갱신 **/
    // 프로필 이미지를 업데이트하는 메서드
    @Override
    @Transactional
    public Long socialLogin(String userEmail, String newProfileImageUrl) {
        // 이메일로 사용자를 찾습니다.
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 사용자가 존재하면 프로필 이미지를 업데이트합니다.
        user.updateProfileImageUrl(newProfileImageUrl);
        // 사용자가 존재하면 로그인 시간을 현재 시간으로 업데이트합니다.
        user.updateLoginDate();
        // 변경된 사용자 엔티티를 저장하여 변경 사항을 반영합니다.
        userRepository.save(user);

        return user.getId();
    }

    /** 회원가입 시 추가 정보 등록 **/
    @Override
    @Transactional // 기본: false (true면 데이터 변경이 안된다.)
    public Long registerUserInfo(String username, @Valid UserInfoRequestDto userDto) {
        // 이메일로 사용자를 찾습니다.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        user.updateBirth(userDto.getBirth());
        user.updateNickname(userDto.getNickname());
        user.updateGender(userDto.getGender());
        user.updateHeight(userDto.getHeight());
        user.updateWeight(userDto.getWeight());
        user.updateIntroduction((userDto.getIntroduction()));
        // 사용자 상태 업데이트
        user.updateUserStatus();

        userRepository.save(user);

        return user.getId();
    }

    /** 회원 상태: 신규 or 기존 **/
    @Override
    public UserStatus getUserStatusByUsername(String username) {
        // 유저 이름을 사용하여 해당 유저를 데이터베이스에서 조회
        User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        return user.getStatus();
    }

    @Override
    // 이메일로 사용자가 존재하는지 확인
    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    // 이메일로 사용자가 존재하는지 확인
    public boolean isUserExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }





    private void validateDuplicateUser(String email) {
        // 이메일 유효성 검사
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원 이메일입니다.");
        }
    }

    private void validateDuplicateNickname(String nickname){
        // 닉네임 유효성 검사
        Optional<User> user = userRepository.findByNickname(nickname);
        if (user.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원 닉네임입니다.");
        }
    }

//    /**
//     * 김부경
//     * explain : 이메일 중복 검사
//     * @param email : 이메일
//     */
//    @Override
//    @Transactional
//    public void checkEmail(String email) {
//        if (userRepository.existsByEmail(email)) {
//            throw new ApiException(ExceptionEnum.EMAIL_EXIST_EXCEPTION);
//        }
//    }
//
//    /**
//     * 김부경
//     * explain : 닉네임 중복 검사
//     * @param nickname : 닉네임
//     */
//    @Override
//    @Transactional
//    public void checkNickname(String nickname) {
//        if (memberRepository.existsByNickname(nickname)) {
//            throw new ApiException(ExceptionEnum.NICKNAME_EXIST_EXCEPTION);
//        }
//    }


    // 회원 조회
    @Override
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

}