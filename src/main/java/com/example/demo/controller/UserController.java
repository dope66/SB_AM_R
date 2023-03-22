package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(UserDto userDto) {
        User user = new User(userDto.getUserName(), userDto.getPassword());
        userRepository.save(user);
        return "redirect:/";
    }
}