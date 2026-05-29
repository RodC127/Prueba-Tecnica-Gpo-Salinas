package com.example.users_api.service;

import com.example.users_api.dto.StudentDto;
import com.example.users_api.entity.Student;
import com.example.users_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentDto> findAll() {
        return studentRepository.findAll()
                .stream().map(this::toDto).toList();
    }

    public StudentDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    public StudentDto create(StudentDto dto) {
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        return toDto(studentRepository.save(toEntity(dto)));
    }

    public StudentDto update(Long id, StudentDto dto) {
        var student = getOrThrow(id);
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        return toDto(studentRepository.save(student));
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Estudiante no encontrado");
        }
        studentRepository.deleteById(id);
    }

    private Student getOrThrow(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado: " + id));
    }

    private StudentDto toDto(Student s) {
        var dto = new StudentDto();
        dto.setControlNumber(s.getControlNumber());
        dto.setName(s.getName());
        dto.setEmail(s.getEmail());
        dto.setPhone(s.getPhone());
        dto.setAddress(s.getAddress());
        return dto;
    }

    private Student toEntity(StudentDto dto) {
        return Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();
    }
}