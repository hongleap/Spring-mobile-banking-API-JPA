package kh.edu.istad.springdatajpa.dto;

public record UpdateAccountRequest(
        Double balance,
        String accountType,
        String actCurrency
) {
}
