package com.example.users_api.service;

import com.example.users_api.dto.StudentsApiResponse;
import com.example.users_api.dto.StudentsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClientService {

    private final RestTemplate restTemplate;

    private static final String API_URL = "http://localhost:8080/api/students";

    public List<StudentsResponseDto> getStudentList(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> raw = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                String.class
        );

        // Parsear manualmente la respuesta
        com.fasterxml.jackson.databind.ObjectMapper mapper =
                new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            var root = mapper.readTree(raw.getBody());
            var dataNode = root.get("data");
            if (dataNode != null && dataNode.isArray()) {
                return mapper.readValue(
                        dataNode.toString(),
                        mapper.getTypeFactory().constructCollectionType(
                                List.class, StudentsResponseDto.class)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
}