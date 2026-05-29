package com.example.users_api.controller;

import com.example.users_api.dto.UserDto;
import com.example.users_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET /api/users — listar todos
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // GET /api/users/{id} — obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // POST /api/users — crear usuario (solo ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> create(
            @Valid @RequestBody UserDto dto,
            @RequestParam String password
    ) {
        return ResponseEntity.status(201).body(userService.create(dto, password));
    }

    // PUT /api/users/{id} — actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UserDto dto
    ) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    // DELETE /api/users/{id} — eliminar usuario (solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Usuario eliminado correctamente"));
    }
}