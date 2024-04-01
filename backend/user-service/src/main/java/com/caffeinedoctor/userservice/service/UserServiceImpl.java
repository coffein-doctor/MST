package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.request.user.UserRequestDto;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    /**
     * 회원 가입
     */
    @Override
    @Transactional // 기본: false (true면 데이터 변경이 안된다.)
    public Long createUser(@Valid UserRequestDto userDto) {
        validateDuplicateUser(userDto.getEmail(), userDto.getNickname()); // 중복 회원 검증

//        User user = User.builder()
//                .uuid(User.generateUUID())  // UUID 생성
//                .email(userDto.getEmail())
//                .nickname(userDto.getNickname())
//                .birth(userDto.getBirth())
//                .gender(userDto.getGender())
//                .height(userDto.getHeight())
//                .weight(userDto.getWeight())
//                .profile(userDto.getProfileImgUrl())  // 프로필 이미지 URL
//                .introduction(userDto.getIntroduction())
//                .build();

        // UserDto -> UserEntity 변환 작업
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);

        return user.getId();
    }

    private void validateDuplicateUser(String email, String nickname) {
        // 이메일 유효성 검사
        List<User> findByEmailUsers = userRepository.findByEmail(email);
        if (!findByEmailUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 이메일입니다.");
        }

        // 닉네임 유효성 검사
        List<User> findByNicknameUsers = userRepository.findByNickname(nickname);
        if (!findByNicknameUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 닉네임입니다.");
        }

    }

    // 회원 조회
    @Override
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

}