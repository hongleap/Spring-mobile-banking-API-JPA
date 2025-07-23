package kh.edu.istad.springdatajpa.dto.account;

import lombok.Builder;

@Builder
public record AccountResponse(
        String accountNumber,
        Double balance,
        String actCurrency
) {
}
