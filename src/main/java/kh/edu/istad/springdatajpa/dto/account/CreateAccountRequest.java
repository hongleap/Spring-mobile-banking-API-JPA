package kh.edu.istad.springdatajpa.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kh.edu.istad.springdatajpa.util.CurrencyUtil;

import java.math.BigDecimal;


public record CreateAccountRequest(

        @NotBlank
        String accountNumber,

        @NotNull
        @Positive
        BigDecimal balance,

        @NotBlank
        CurrencyUtil actCurrency,

        @NotNull
        Boolean isDeleted,

        @NotNull
        Integer customerId,

        @NotBlank
        String segment,

        String phoneNumber,
        String accountType

) {
}
