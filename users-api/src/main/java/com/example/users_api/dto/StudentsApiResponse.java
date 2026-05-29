package com.example.users_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class StudentsApiResponse {

    private int statusCode;
    private String message;
    private List<StudentsResponseDto> students;
}