package com.example.users_api.dto;

import lombok.Data;

@Data
public class StudentsResponseDto {
    private Long controlNumber;
    private String name;
    private String email;
    private String phone;
    private AddressDto address;

    @Data
    public static class AddressDto {
        private String street;
        private String neighborhood;
        private String city;
    }
}