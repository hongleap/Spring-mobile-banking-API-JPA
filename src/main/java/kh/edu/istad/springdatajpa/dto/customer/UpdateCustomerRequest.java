package kh.edu.istad.springdatajpa.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}
