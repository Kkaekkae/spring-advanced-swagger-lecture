package com.sparta.basic4.application.dtos.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank
    @Size(min = 8, max = 20)
    private String username;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
}