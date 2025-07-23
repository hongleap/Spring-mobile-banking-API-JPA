package kh.edu.istad.springdatajpa.dto.customer;

import lombok.Builder;

@Builder
public record CustomerResponse (
        String fullName,
        String gender,
        String email
){
}
