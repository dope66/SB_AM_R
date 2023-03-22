package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;


@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(UserDto userDto, Model model) {
        if (userDto.getUserName().isEmpty() || userDto.getPassword().isEmpty()) {
            model.addAttribute("error", "Please enter your username and password.");
            return "signup";
        } else {
            User user = new User(userDto.getUserName(), userDto.getPassword());
            userRepository.save(user);
            model.addAttribute("message", "Signup completed successfully.");
            return "signup-complete";
        }
    }
    @GetMapping("/signup-complete")
    public String signupComplete(Model model) {
        return "signup-complete";
    }
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(UserDto userDto, Model model) {
        Optional<User> optionalUser = userRepository.findByUserName(userDto.getUserName());
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(userDto.getPassword())) {
            logger.debug("Login successful for username: " + userDto.getUserName());
            return "redirect:/home";
        } else {
            logger.debug("Login failed for username: " + userDto.getUserName());
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

}