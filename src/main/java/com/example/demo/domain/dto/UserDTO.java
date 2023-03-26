package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserDTO {
    @JsonProperty(value = "username")
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 6)
    private String username;

    @JsonProperty(value = "password")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8)
    private String password;

    @JsonProperty(value = "email")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;
}
