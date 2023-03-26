package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserDTO {
    @JsonProperty(value = "username")
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 6, message = "사용자의 이름은 최소 6자리 이상이어야 합니다")
    private String username;

    @JsonProperty(value = "password")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자리 이상이어야 합니다.")
    private String password;

    @JsonProperty(value = "email")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;


}
