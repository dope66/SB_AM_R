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

    public boolean isUsernameExists(String username) {
        return userRepository.findByUserName(username).isPresent();
    }

    public void registerUser(UserDto userDto) throws IllegalArgumentException {
        if (userDto.getUserName() == null) {
            throw new IllegalArgumentException("userName을 입력해주세요.");
        }

        if (isUsernameExists(userDto.getUserName())) {
            throw new IllegalArgumentException("이미 가입된 사용자 이름입니다.");
        }

        if (userDto.getPassword() == null) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }

        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);
    }
}