package kh.edu.istad.springdatajpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record CreateAccountRequest(

        @NotBlank
        String accountNumber,

        @NotBlank
        String accountType,

        @NotBlank
        String actCurrency,

        @NotNull
        @Positive
        Double balance,

        @NotNull
        Boolean isDeleted,


        @NotNull
        String customerPhoneNumber

) {
}
