package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.exception.AppException;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService   {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secretKey}")
    private String secretKey;
    private final Long expiredMs =60 * 60 * 24 * 30l;

    public String login(String username, String password) {
        // username이 없을 경우
        User selectedUser = userRepository.findByUsername(username)
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,username +"이 없습니다."));
        // password 틀림
        if(!encoder.matches(password,selectedUser.getPassword())){
            System.out.println("아 ㅋㅋ패스워드 잘못누름 ");
            throw new AppException(ErrorCode.INVALID_PASSWORD,"Password를 잘못 입력 하였습니다.");
        }
        System.out.println("서비스 login을 통과해 creatJWt");
        return JwtUtil.createJwt(username, secretKey, expiredMs);
    }

    public void join(String username, String password){
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
    }

}