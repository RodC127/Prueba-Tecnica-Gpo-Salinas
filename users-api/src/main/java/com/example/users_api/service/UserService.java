package com.example.users_api.service;

import com.example.users_api.dto.UserDto;
import com.example.users_api.entity.User;
import com.example.users_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(this::toDto).toList();
    }

    public UserDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    public UserDto create(UserDto dto, String rawPassword) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        var user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(rawPassword))
                .role(dto.getRole() != null ? dto.getRole() : User.Role.USER)
                .build();
        return toDto(userRepository.save(user));
    }

    public UserDto update(Long id, UserDto dto) {
        var user = getOrThrow(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        if (dto.getRole() != null) user.setRole(dto.getRole());
        return toDto(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) throw new IllegalArgumentException("Usuario no encontrado");
        userRepository.deleteById(id);
    }

    private User getOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
    }

    private UserDto toDto(User u) {
        var dto = new UserDto();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole());
        return dto;
    }
}