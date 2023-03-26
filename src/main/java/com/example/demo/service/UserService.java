package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.exception.UsernameAlreadyExistsException;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface UserService {
    User registerNewUser(UserDTO userDTO) throws UsernameAlreadyExistsException;


    Map<String, String> validateHandler(BindingResult bindingResult);
}
