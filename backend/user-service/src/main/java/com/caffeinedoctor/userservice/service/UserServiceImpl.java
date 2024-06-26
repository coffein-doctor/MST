package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.common.util.HealthProfileUtil;
import com.caffeinedoctor.userservice.dto.response.TokenStatusDto;
import com.caffeinedoctor.userservice.dto.response.user.MypageDto;
import com.caffeinedoctor.userservice.dto.response.user.SearchUserInfoDto;
import com.caffeinedoctor.userservice.dto.response.user.UserDetailsDto;
import com.caffeinedoctor.userservice.dto.socialLoginDto;
import com.caffeinedoctor.userservice.dto.request.user.UserInfoRequestDto;
import com.caffeinedoctor.userservice.entitiy.Follow;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.enums.UserStatus;
import com.caffeinedoctor.userservice.repository.FollowRepository;
import com.caffeinedoctor.userservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true) // (성능 최적화 - 읽기 전용에만 사용)
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final TokenService tokenService;

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

    /** 회원 가입 추가 정보 등록 **/
    @Override
    @Transactional
    public Long createUser(String username, UserInfoRequestDto userDto) {
        User user = findUserByUsername(username);
        // 사용자 정보 업데이트
        updateUserDetails(user, userDto);
        // 사용자 상태 업데이트 (우리 서비스에 회원 가입 완료)
        user.updateUserStatus();
        userRepository.save(user);
        return user.getId();
    }

    /** 회원 정보 수정 추가 정보 등록 **/
    @Override
    @Transactional
    public UserDetailsDto updateUser(Long userId, String username, UserInfoRequestDto userDto) {
        User user = findUserByUsername(username);
        // 찾은 사용자의 userId와 입력받은 userId가 일치하는지 확인합니다.
        verifyUserAuthentication(user, userId);

        // 사용자 정보 업데이트
        updateUserDetails(user, userDto);
        userRepository.save(user);
        return createUserDetailsDto(user);
    }

    /** 회원 정보 디테일 dto **/
    private UserDetailsDto createUserDetailsDto(User user) {
        return UserDetailsDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .status(user.getStatus())
                .nickname(user.getNickname())
                .birth(user.getBirth())
                .gender(user.getGender())
                .height(user.getHeight())
                .weight(user.getWeight())
                .activityLevel(user.getActivityLevel())
                .profileImageUrl(user.getProfileImageUrl())
                .introduction(user.getIntroduction())
                .build();
    }

    /** 추가 정보 등록 **/
    // 사용자 정보 업데이트 로직을 별도의 메서드로 추출합니다.
    private void updateUserDetails(User user, UserInfoRequestDto userDto) {
        user.updateBirth(userDto.getBirth());
        user.updateNickname(userDto.getNickname());
        user.updateGender(userDto.getGender());
        user.updateActivityLevel(userDto.getActivityLevel());
        user.updateHeight(userDto.getHeight());
        user.updateWeight(userDto.getWeight());
        user.updateIntroduction(userDto.getIntroduction());
    }

    /** 회원 완전 삭제 **/
    @Override
    @Transactional
    public void hardDeleteUser(HttpServletRequest request, HttpServletResponse response, Long userId, String username) {
        // 유저 찾기
        User user = findUserByUsername(username);
        // 찾은 사용자의 userId와 입력받은 userId가 일치하는지 확인합니다.
        verifyUserAuthentication(user, userId);

        // 유저와 관련된 토큰 삭제
        TokenStatusDto tokenStatusDto = tokenService.removeAllToken(request, response, username);
        if (!tokenStatusDto.isSuccessful()) {
            log.error("토큰 삭제 실패: {}", tokenStatusDto.getMessage());
            // 토큰 삭제 실패 시 예외를 throw하여 트랜잭션 롤백 유도
            throw new RuntimeException(tokenStatusDto.getMessage());
        }
        userRepository.deleteById(userId);
    }

    /** 회원 조회 **/
    @Override
    public UserDetailsDto getUserDetailsById(Long userId){
        // 유저 ID를 사용하여 해당 유저를 데이터베이스에서 조회
        User user = findUserById(userId);
        return createUserDetailsDto(user);
    }

    /////////////////////////////////** 마이페이지 관련 **///////////////////////////
    /** 마이페이지 정보 조회 **/
    public MypageDto getUserMypageInfo(Long userId){
       User user = findUserById(userId);
        double recommendedSugarIntake = HealthProfileUtil
                .calculateRecommendedSugarIntake(user.getHeight(), user.getGender(), user.getActivityLevel());
        long followingCount = followRepository.countAllByFromUserId(userId);
        long followerCount = followRepository.countAllByToUserId(userId);

        return MypageDto.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .profilePictureUrl(user.getProfileImageUrl())
                .introduction(user.getIntroduction())
                .followingCount(followingCount)
                .followerCount(followerCount)
                .recommendedDailySugarIntake(recommendedSugarIntake)
                .build();
    }

    /////////////////////////////////** 팔로우 관련 **///////////////////////////
    /** 회원 검색 **/
    public SearchUserInfoDto searchUserByNickname(String nickname) {
        User user = findUserByNickname(nickname);

        if (user.getStatus() == UserStatus.NEW_USER) {
            throw new IllegalStateException(nickname + " has not yet completed the registration.");
        }

        return SearchUserInfoDto.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .introduction(user.getIntroduction())
                .build();
    }

    /** 회원 팔로잉 목록 조회 **/
    @Override
    public List<SearchUserInfoDto> getFollowingUsers(Long userId) {
        // 내가 팔로우하는 팔로우 엔티티 목록 가져오기
        List<Follow> follows = followRepository.findAllByFromUserId(userId);
        List<SearchUserInfoDto> followingUsers = new ArrayList<>();

        for (Follow follow : follows) {
            User user = follow.getToUser();
            SearchUserInfoDto followingUser = SearchUserInfoDto.builder()
                    .userId(user.getId())
                    .nickname(user.getNickname())
                    .profileImageUrl(user.getProfileImageUrl())
                    .introduction(user.getIntroduction())
                    .build();

            followingUsers.add(followingUser);
        }

        return followingUsers;
    }

    /** 회원 팔로워 목록 조회 **/
    public List<SearchUserInfoDto> getFollowerUsers(Long userId) {
        // 나를 팔로우하는 follow 목록 가져오기
        List<Follow> follows = followRepository.findAllByToUserId(userId);
        List<SearchUserInfoDto> followerUsers = new ArrayList<>();

        for (Follow follow : follows) {
            User user = follow.getFromUser();
            SearchUserInfoDto followerUser = SearchUserInfoDto.builder()
                    .userId(user.getId())
                    .nickname(user.getNickname())
                    .profileImageUrl(user.getProfileImageUrl())
                    .introduction(user.getIntroduction())
                    .build();

            followerUsers.add(followerUser);
        }
        return followerUsers;
    }

    /** 회원 Id 조회 **/
    @Override
    public Long getUserId(String username){
        // 유저 ID를 사용하여 해당 유저를 데이터베이스에서 조회
        User user = findUserByUsername(username);
        return user.getId();
    }

    /** 회원 상태: 신규 or 기존 **/
    @Override
    public UserStatus getUserStatusByUsername(String username) {
        // 유저 이름을 사용하여 해당 유저를 데이터베이스에서 조회
        User user = findUserByUsername(username);
        return user.getStatus();
    }

    /** 회원 찾기 **/
    // 로그인 아이디(username)로 사용자를 찾습니다.
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
    }

    // 회원 아이디로 회원 찾기
    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID에 대한 사용자를 찾을 수 없습니다."));

    }

    // 닉네임으로 회원 찾기
    private User findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new EntityNotFoundException("User with nickname " + nickname + " not found"));
    }

    /** 검증 및 유효성 검사 **/
    // 변경하려고 전달해준 유저의 Id와 값을 보낸 유저가 같은 유저인지 검증
    private void verifyUserAuthentication(User user, Long UserId) throws AccessDeniedException {
        // 찾은 사용자의 userId와 입력받은 userId가 일치하는지 확인합니다.
        if (!user.getId().equals(UserId)) {
            throw new AccessDeniedException("해당 작업을 수행할 권리가 없습니다. 로그인된 사용자 Id와 일치하는 사용자 Id가 아닙니다.");
        }
    }

    /** 존재 여부 확인 **/
    // 이메일로 사용자 존재 여부 확인
    @Override
    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // 아이디로 사용자 존재 여부 확인
    @Override
    public boolean isUserExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // 같은 닉네임 존재 여부 확인
    @Override
    public boolean isNicknameExists(String nickname){
        // 닉네임 유효성 검사
        return userRepository.existsByNickname(nickname);
    }

}
