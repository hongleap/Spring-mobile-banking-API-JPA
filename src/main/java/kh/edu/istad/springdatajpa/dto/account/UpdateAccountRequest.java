package kh.edu.istad.springdatajpa.dto.account;

public record UpdateAccountRequest(
        Double balance,
        String actCurrency
) {
}
