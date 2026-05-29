package com.example.users_api.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;
}