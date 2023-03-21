package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public String join(String userName ,String password){
        // username중복 체크
        userRepository.findByUserName(userName)
                .ifPresent(user -> {throw new RuntimeException(userName+"는 이미 있씁니다.");
                });
        // 저장
        User user = User.builder()
                .userName(userName)
                .password(password)
                .build();
        userRepository.save(user);
        return "SUCCESS";
    }
}
