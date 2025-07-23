package kh.edu.istad.springdatajpa.service;

import kh.edu.istad.springdatajpa.dto.account.AccountResponse;
import kh.edu.istad.springdatajpa.dto.account.CreateAccountRequest;
import kh.edu.istad.springdatajpa.dto.account.UpdateAccountRequest;

import java.util.List;

public interface AccountService {

    AccountResponse createNew(CreateAccountRequest createAccountRequest);

    List<AccountResponse> findAllAccounts();

    AccountResponse findAccountByAccountNumber(String accountNumber);

    List<AccountResponse> findAccountByCustomerId(Integer customerId);

    void deleteAccountByAccountNumber(String accountNumber);

    void deleteAccountByCustomerId(Integer customerId);

    AccountResponse updateAccountByAccountNumber(String accountNumber, UpdateAccountRequest updateAccountRequest);

}
