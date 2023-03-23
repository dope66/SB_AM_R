package com.example.demo.controller;

import com.example.demo.domain.LoginRequest;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDTO userDTO){
        userService.join(userDTO.getUsername(),userDTO.getPassword());
        return ResponseEntity.ok().body("회원가입 성공했습니다.");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest dto){
        return ResponseEntity.ok().body(userService.login(dto.getUsername(),""));

    }


}