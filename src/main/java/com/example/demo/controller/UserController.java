package com.example.demo.controller;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor   //서비스 추가후 생성
@RequestMapping("/usr/member")
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok().body("token");
    }

//    @PostMapping("/join")
//    public  ResponseEntity<?> join(@RequestBody UserJoinRequest dto){
//        userService.join(dto.getUserName(),dto.getPassword());
//        return ResponseEntity.ok().body("회원가입완료");
//    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            userService.registerUser(userDto);
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
