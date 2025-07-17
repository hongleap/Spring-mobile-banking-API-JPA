package kh.edu.istad.springdatajpa.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(

        @NotBlank(message = "Full Name is required")
        String fullName,

        @NotBlank(message = "Full Name is required")
        String gender,

        String email,

        String phoneNumber,

        String remark
) {
}
