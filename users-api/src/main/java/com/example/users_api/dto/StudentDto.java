package com.example.users_api.dto;

import com.example.users_api.entity.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDto {

    private Long controlNumber;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;

    private Student.Address address;
}