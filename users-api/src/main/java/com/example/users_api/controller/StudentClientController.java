package com.example.users_api.controller;

import com.example.users_api.dto.ApiResponse;
import com.example.users_api.dto.StudentsResponseDto;
import com.example.users_api.service.StudentClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client/students")
@RequiredArgsConstructor
public class StudentClientController {

    private final StudentClientService studentClientService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentsResponseDto>>> getStudents(
            @RequestHeader("Authorization") String token) {

        var students = studentClientService.getStudentList(token);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "OK - Listado consumido exitosamente", students)
        );
    }
}