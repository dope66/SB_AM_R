package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secretKey}")
    private String secretKey;
    private final Long expiredMs =60 * 60 * 24 * 30l;

    public String login(String username, String password) {

        return JwtUtil.createJwt(username, secretKey, expiredMs);
    }

    public String join(String username, String password){
        // 중복 체크 (현재 username);
        userRepository.findByUsername(username)
                .ifPresent(user ->{
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, username + "는 이미 존재합니다");
                } );
        //저장
        User user= User.builder()
                .username(username)
                .password(encoder.encode(password))
                        .build();
        userRepository.save(user);

        return "Success";
    }
}