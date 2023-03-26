package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    @Size(min = 6)
    private String username;
    @Column(nullable = false)
    @NotBlank
    @Size(min = 6)
    private String password;
    @Column(nullable = false)
    @NotBlank
    private String email;
    private String role = "USER"; //ROLE_USER, ROLE_ADMIN
    @CreationTimestamp
    private Timestamp createDate;

}