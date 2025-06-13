package com.yuri.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "O campo nome é obrigatório.")
    private String name;

    @Email(message = "Email inválido.")
    @NotBlank(message = "O campo email é obrigatório.")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório.")
    private String password;
}