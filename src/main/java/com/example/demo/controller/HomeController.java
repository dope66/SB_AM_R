package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello, world!");
        return "home";
    }
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            // 로그인 상태일 경우
            model.addAttribute("username", principal.getName());
            model.addAttribute("loggedIn", true);
        } else { // 로그인 상태가 아닐 경우
            model.addAttribute("loggedIn", false);
        }
        return "home";
    }
}