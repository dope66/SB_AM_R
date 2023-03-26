package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            model.addAttribute("message", "로그인이 필요합니다.");
        } else {
            String currentUserName = authentication.getName();
            model.addAttribute("username", currentUserName);

        }
        return "home";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new User());
        return "join";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/joinProc")
    public String joinProc(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            if (bindingResult.getFieldError("username") != null) {
                model.addAttribute("usernameError", "사용자의 이름은 최소 6자리 이상이어야합니다.");
            }
            if (bindingResult.getFieldError("password") != null) {
                model.addAttribute("passwordError", "비밀번호는 최소 8자리 이상이어야합니다.");
            }
            return "join"; // 에러 메시지와 함께 다시 회원가입 페이지로 돌아갑니다.
        }
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/";
    }

}