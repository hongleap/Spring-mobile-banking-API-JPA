package kh.edu.istad.springdatajpa.dto;

import lombok.Builder;

@Builder
public record AccountResponse(
        String accountNumber,
        Double balance,
        String accountType,
        String actCurrency
) {
}
