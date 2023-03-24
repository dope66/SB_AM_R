package com.example.demo.controller;

import com.example.demo.domain.LoginRequest;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //    @PostMapping("/join")
//    public ResponseEntity<String> join(@RequestBody UserDTO userDTO){
//        userService.join(userDTO.getUsername(),userDTO.getPassword());
//        return ResponseEntity.ok().body("회원가입 성공했습니다.");
//
//    }
    @PostMapping("/join")
    public String join(@ModelAttribute UserDTO userDTO) {
        userService.join(userDTO.getUsername(), userDTO.getPassword());
//        return ResponseEntity.ok().body("회원가입 성공했습니다.");
        return "/home";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "join";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("UserDTO", new UserDTO());
        return "login"; // login.html 등의 로그인 페이지를 반환
    }
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
////        return ResponseEntity.ok().body(userService.login(dto.getUsername(),""));
//        String token = userService.login(userDTO.getUsername(),userDTO.getPassword());
//        return ResponseEntity.ok().body(token);
//
//    }

    //     토큰을 이용한 로그인은 성공
    @PostMapping("/login")
    public ResponseEntity<String> login(@ModelAttribute UserDTO userDTO, Model model) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        System.out.println("username: " + username);
        System.out.println("password: " + password);
        // 로그인 처리 로직
        String token = userService.login(username, password);

        if (token != null) {
            // 로그인 성공 시 JWT 토큰을 HTTP 헤더에 담아서 전송
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return ResponseEntity.ok().headers(headers).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Location", "/home")
                    .build();
        }
    }
//    @PostMapping("/login")
//    public String login(@ModelAttribute UserDTO userDTO, HttpServletResponse response, Model model) {
//        String username = userDTO.getUsername();
//        String password = userDTO.getPassword();
//
//        // 로그인 처리 로직
//        String token = userService.login(username, password);
//
//        if (token != null) {
//            // JWT 토큰을 HTTP 쿠키에 저장
//            Cookie jwtCookie = new Cookie("jwt", token);
//            jwtCookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키 유효 기간 7일로 설정
//            jwtCookie.setHttpOnly(true);
//            response.addCookie(jwtCookie);
//
//            // 로그인 성공 메시지 출력 및 홈으로 리다이렉트
//            model.addAttribute("message", "로그인에 성공했습니다.");
//            return "redirect:/";
//        } else {
//            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
//            return "login";
//        }



}
