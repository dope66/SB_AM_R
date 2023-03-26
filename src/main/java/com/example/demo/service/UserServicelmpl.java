package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.exception.UsernameAlreadyExistsException;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServicelmpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServicelmpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public User registerNewUser(UserDTO userDTO) throws UsernameAlreadyExistsException {
        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername());

        if (existingUser.isPresent()) {
            throw new UsernameAlreadyExistsException("이미 사용중인 이름입니다.");
        }

        String rawPassword = userDTO.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(encPassword);

        return userRepository.save(newUser);
    }

    @Override
    public Map<String, String> validateHandler(BindingResult bindingResult) {
        Map<String, String> validateResult = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            String validKeyName = "valid_" + error.getField();
            validateResult.put(validKeyName, error.getDefaultMessage());
        }

        return validateResult;
    }

}
