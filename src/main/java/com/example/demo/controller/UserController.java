package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.exception.UsernameAlreadyExistsException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
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

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
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
    public String joinProc(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) throws UsernameAlreadyExistsException {
        // 사용자가 입력한 정보 출력
        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
        System.out.println(userDTO.getEmail());
        // 입력값 유효성 검사
        if (bindingResult.hasErrors()) {
            // 입력값 유지
            model.addAttribute("user", userDTO);
            Map<String, String> validateResult = userService.validateHandler(bindingResult);
            for (String key : validateResult.keySet()) {
                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
                model.addAttribute(key, validateResult.get(key));
            }
            return "join";
        }
        try {
            //사용자 등록
            User newUser = userService.registerNewUser(userDTO);
            return "redirect:/";
        } catch (UsernameAlreadyExistsException e) {
            model.addAttribute("valid_username", "이미 사용중인 아이디입니다.");
            model.addAttribute("user", userDTO);
            return "join";
        }

    }
}