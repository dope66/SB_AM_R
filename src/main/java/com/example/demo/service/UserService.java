package com.example.demo.service;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    public String join(String userName ,String password){
//        // username중복 체크
//        userRepository.findByUserName(userName)
//                .ifPresent(user -> {throw new RuntimeException(userName+"는 이미 있씁니다.");
//                });
//        // 저장
//        User user = User.builder()
//                .userName(userName)
//                .password(password)
//                .build();
//        userRepository.save(user);
//        return "SUCCESS";
//    }

    public boolean isUserNameExists(String userName) {
        User user = userRepository.findByUserName(userName);
        return user != null;
    }

    public void registerUser(UserDto userDto) throws Exception {

        if (userDto.getUserName() == null) {
            throw new Exception("userName을 입력해주세요.");
        }
        if (isUserNameExists(userDto.getUserName())) {
            throw new Exception("이미 가입된 userName입니다");
        }

        User user = new User();
        user.setUserName(userDto.getUserName());

        String password = userDto.getPassword();
        if (password == null || password.isEmpty()) {
//            password="defaultPassword";
            throw new Exception("비밀번호를 입력해주세요.");
        }
        // password 암호화 등 적절한 처리 수행
        user.setPassword(password);
        userRepository.save(user);
    }

}
