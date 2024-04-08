package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.enums.UserType;
import com.caffeinedoctor.userservice.dto.request.user.UserRequestDto;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true) // (성능 최적화 - 읽기 전용에만 사용)
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    @Autowired // 생성자 주입
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    // 프로필 이미지를 업데이트하는 메서드
    public void updateProfileImage(String userEmail, String newProfileImageUrl) {
        // 이메일로 사용자를 찾습니다.
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 사용자가 존재하면 프로필 이미지를 업데이트합니다.
        user.updateProfileImageUrl(newProfileImageUrl);

        // 변경된 사용자 엔티티를 저장하여 변경 사항을 반영합니다.
        userRepository.save(user);
    }

    public void updateLoginDate(String userEmail) {
        // 이메일로 사용자를 찾습니다.
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 사용자가 존재하면 로그인 시간을 현재 시간으로 업데이트합니다.
        user.updateLoginDate();

        // 변경된 사용자 엔티티를 저장하여 변경 사항을 반영합니다.
        userRepository.save(user);
    }

    // 이메일로 사용자가 존재하는지 확인
    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    // 소셜로그인 로직에서 회원 찾기
    public UserType checkUserTypeByEmail(String email) {
        // 이메일을 사용하여 사용자의 존재 여부 확인
        boolean userExists = userRepository.existsByEmail(email);

        if (userExists) {
            // 기존 회원인 경우
            return UserType.EXISTING_MEMBER;
        } else {
            // 신규 회원인 경우
            return UserType.NEW_MEMBER;
        }
    }

    /**
     * 회원 가입
     */
    @Override
    @Transactional // 기본: false (true면 데이터 변경이 안된다.)
    public Long signUp(@Valid UserRequestDto userDto) {
        // 중복 회원 검증

        User user = User.builder()
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .birth(userDto.getBirth())
                .gender(userDto.getGender())
                .height(userDto.getHeight())
                .weight(userDto.getWeight())
                .profileImageUrl(userDto.getProfileImgUrl())
                .introduction(userDto.getIntroduction())
                .build();

        // UserDto -> UserEntity 변환 작업
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        User user = mapper.map(userDto, User.class);
        userRepository.save(user);

        return user.getId();
    }


    private void validateDuplicateUser(String email) {
        // 이메일 유효성 검사
        Optional user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원 이메일입니다.");
        }
    }
    private void validateDuplicateNickname(String nickname){
        // 닉네임 유효성 검사
        Optional user = userRepository.findByNickname(nickname);
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
