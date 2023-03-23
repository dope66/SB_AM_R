package com.example.demo.domain.dto;

import com.example.demo.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)

                .build();
    }


}
