package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usr/member/reviews")
public class ReviewController {
    @PostMapping
    public ResponseEntity<String> writeReview(){
        return ResponseEntity.ok().body("등록완료");
    }

}
