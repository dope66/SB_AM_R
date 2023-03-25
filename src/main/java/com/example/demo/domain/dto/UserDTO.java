package com.example.demo.domain.dto;

import com.example.demo.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserDTO {
    @JsonProperty(value="username")
    private String username;
    @JsonProperty(value = "password")
    private String password;

}
