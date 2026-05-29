package com.example.users_api.dto;

import com.example.users_api.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank @Email(message = "Email inválido")
    private String email;

    private User.Role role;
}