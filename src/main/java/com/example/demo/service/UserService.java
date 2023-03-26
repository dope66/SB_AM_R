package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.exception.UsernameAlreadyExistsException;

public interface UserService {
    User registerNewUser(UserDTO userDTO) throws UsernameAlreadyExistsException;
}
