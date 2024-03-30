package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.entitiy.enums.Gender;
import com.caffeinedoctor.userservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Test
    public void 회원가입() throws Exception {
        //Given (주어졌을 때)
        User user = new User();
        user.setEmail("kim");
        user.setBirth(LocalDate.now());
        user.setHeight(60);
        user.setNickname("kkk");
        user.setIntroduction("hi");
        user.setWeight(50);
        user.setLoginDate(LocalDateTime.now());
        user.setSignUpDate(LocalDateTime.now());
        Gender gender = Gender.FEMALE;
        user.setGender(gender);
        //When (실행하면)
        Long saveId = userService.join(user);
        //Then (이렇게 나와야 해)
        Optional<User> userOptional = userRepository.findById(saveId);
        assertTrue(userOptional.isPresent()); // findById로 조회된 사용자가 존재하는지 확인
    }
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //Given
        User user1 = new User();
        user1.setEmail("kim");
        user1.setBirth(LocalDate.now());
        user1.setHeight(160);
        user1.setNickname("kkk");
        user1.setIntroduction("hi");
        user1.setWeight(50);
        user1.setLoginDate(LocalDateTime.now());
        user1.setSignUpDate(LocalDateTime.now());
        Gender gender1 = Gender.FEMALE;
        user1.setGender(gender1);

        User user2 = new User();
        user2.setEmail("kim");
        user2.setBirth(LocalDate.now());
        user2.setHeight(260);
        user2.setNickname("kkk");
        user2.setIntroduction("hi");
        user2.setWeight(50);
        user2.setLoginDate(LocalDateTime.now());
        user2.setSignUpDate(LocalDateTime.now());
        Gender gender2 = Gender.FEMALE;
        user2.setGender(gender2);
        //When
        userService.join(user1);
        userService.join(user2); //예외가 발생해야 한다.
        //Then
        fail("예외가 발생해야 한다.");
    }
}