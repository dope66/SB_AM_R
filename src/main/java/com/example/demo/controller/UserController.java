package com.example.demo.controller;

import com.example.demo.domain.dto.UserJoinRequest;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor   //서비스 추가후 생성
@RequestMapping("/usr/member")
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok().body("token");
    }

    @GetMapping("/join")
    public  ResponseEntity<String> join(@RequestParam(required = false,name="dto") UserJoinRequest dto){
        return ResponseEntity.ok().body("회원가입완료");
    }

}
