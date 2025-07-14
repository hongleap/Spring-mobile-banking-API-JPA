package kh.edu.istad.springdatajpa.service;

import kh.edu.istad.springdatajpa.dto.AccountResponse;
import kh.edu.istad.springdatajpa.dto.CreateAccountRequest;
import kh.edu.istad.springdatajpa.dto.UpdateAccountRequest;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest createAccountRequest);

    List<AccountResponse> findAllAccounts();

    AccountResponse findAccountByAccountNumber(String accountNumber);

    List<AccountResponse> findAccountByCustomerId(Integer customerId);

    void deleteAccountByAccountNumber(String accountNumber);

    void deleteAccountByCustomerId(Integer customerId);

    void updateAccountByAccountNumber(String accountNumber, UpdateAccountRequest updateAccountRequest);
}
