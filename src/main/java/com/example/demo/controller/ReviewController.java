package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class ReviewController {
    @PostMapping("/reviews")
    public ResponseEntity<String> writeReview(){
        return ResponseEntity.ok().body("등록완료");
    }

}
