package kh.edu.istad.springdatajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kh.edu.istad.springdatajpa.domain.Account;
import kh.edu.istad.springdatajpa.domain.AccountType;
import kh.edu.istad.springdatajpa.domain.Customer;
import kh.edu.istad.springdatajpa.dto.account.AccountResponse;
import kh.edu.istad.springdatajpa.dto.account.CreateAccountRequest;
import kh.edu.istad.springdatajpa.dto.account.UpdateAccountRequest;
import kh.edu.istad.springdatajpa.mapper.AccountMapper;
import kh.edu.istad.springdatajpa.repository.AccountRepository;
import kh.edu.istad.springdatajpa.repository.AccountTypeRepository;
import kh.edu.istad.springdatajpa.repository.CustomerRepository;
import kh.edu.istad.springdatajpa.service.AccountService;
import kh.edu.istad.springdatajpa.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public  class AccountServiceImpl implements AccountService {

    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createNew(CreateAccountRequest createAccountRequest) {
        Customer customer = customerRepository
                .findByPhoneNumber(createAccountRequest.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer phone number not found"));

        AccountType accountType = accountTypeRepository
                .findByType(createAccountRequest.accountType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account type not found"));

        Account account = accountMapper.toAccount(createAccountRequest);
        account.setAccountType(accountType);
        account.setCustomer(customer);

        if (account.getAccountNumber().isBlank()) { // Auto generate
            String accountNumber;
            do {
                accountNumber = String.format("%09d", new Random().nextInt(1_000_000_000)); // Max: 999,999,999
            } while (accountRepository.existsByAccountNumber(accountNumber));
            account.setAccountNumber(accountNumber);
        } else { // From DTO, check validation actNo
            if (accountRepository.existsByAccountNumber(account.getAccountNumber())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Account number already exists");
            }
        }

        account.setIsHide(false);
        account.setIsDeleted(false);
        account.setActCurrency(createAccountRequest.actCurrency().name());

        if (account.getCustomer().getCustomerSegment().getCustomerSegment().equals("REGULAR")) {
            account.setOverLimit(BigDecimal.valueOf(5000));
        } else if (account.getCustomer().getCustomerSegment().getCustomerSegment().equals("SILVER")) {
            account.setOverLimit(BigDecimal.valueOf(50000));
        } else {
            account.setOverLimit(BigDecimal.valueOf(100000));
        }

        // Validate balance
        switch (createAccountRequest.actCurrency()) {
            case CurrencyUtil.DOLLAR -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.TEN) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Balance must be greater than 10 dollars");
                }
            }
            case CurrencyUtil.RIEL -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(40000)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Balance must be greater than 40000 Riels");
                }
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Currency is not available");
        }

        account = accountRepository.save(account);

        return accountMapper.fromAccount(account);
    }


    @Override
    public List<AccountResponse> findAllAccounts() {

        return accountRepository
                .findAll()
                .stream()
                .map(accountMapper::fromAccount).toList();
    }

    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        return accountMapper.fromAccount(account);
    }

    @Override
    public List<AccountResponse> findAccountByCustomerId(Integer customerId) {

        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        List<Account> accounts = accountRepository
                .findAccountByCustomerId(customerId);

        return accounts
                .stream()
                .map(accountMapper::fromAccount).toList();
    }

    @Override
    public void deleteAccountByAccountNumber(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        accountRepository.delete(account);

    }

    @Override
    public void deleteAccountByCustomerId(Integer customerId) {

        List<Account> accounts = accountRepository
                .findAccountByCustomerId(customerId);

        accountRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID not found"));

        accountRepository.deleteAccountByCustomerId(customerId);

    }

    @Override
    public AccountResponse updateAccountByAccountNumber(String accountNumber, UpdateAccountRequest updateAccountRequest) {
        int updated = accountRepository.disableAccountByAccountNumber(accountNumber);
        if (updated == 0) {
            throw new EntityNotFoundException("Account not found with accountNumber: " + accountNumber);
        }
        return null;
    }
}
