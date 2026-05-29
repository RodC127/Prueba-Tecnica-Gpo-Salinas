package com.example.users_api.controller;

import com.example.users_api.dto.ApiResponse;
import com.example.users_api.dto.StudentDto;
import com.example.users_api.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDto>>> getAll() {
        var students = studentService.findAll();
        return ResponseEntity.ok(
                new ApiResponse<>(200, "OK - Listado obtenido exitosamente", students)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "OK - Estudiante encontrado", studentService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentDto>> create(@Valid @RequestBody StudentDto dto) {
        return ResponseEntity.status(201).body(
                new ApiResponse<>(201, "Estudiante creado exitosamente", studentService.create(dto))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentDto dto) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Estudiante actualizado exitosamente", studentService.update(id, dto))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Estudiante eliminado correctamente", null)
        );
    }
}