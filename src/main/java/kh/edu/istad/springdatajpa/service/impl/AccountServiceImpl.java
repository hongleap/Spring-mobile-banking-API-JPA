package kh.edu.istad.springdatajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kh.edu.istad.springdatajpa.domain.Account;
import kh.edu.istad.springdatajpa.domain.Customer;
import kh.edu.istad.springdatajpa.dto.AccountResponse;
import kh.edu.istad.springdatajpa.dto.CreateAccountRequest;
import kh.edu.istad.springdatajpa.dto.UpdateAccountRequest;
import kh.edu.istad.springdatajpa.mapper.AccountMapper;
import kh.edu.istad.springdatajpa.repository.AccountRepository;
import kh.edu.istad.springdatajpa.repository.CustomerRepository;
import kh.edu.istad.springdatajpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {

        if(accountRepository.existsByAccountNumber(createAccountRequest.accountNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, " Account number already in use");
        }

        Customer customer  = customerRepository.findById(createAccountRequest.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Account account = new Account();
        account.setAccountNumber(createAccountRequest.accountNumber());
        account.setBalance(createAccountRequest.balance());
        account.setAccountType(createAccountRequest.accountType());
        account.setActCurrency(createAccountRequest.actCurrency());
        account.setIsDeleted(false);
        account.setCustomer(customer);
        accountRepository.save(account);
        return accountMapper.fromAccount(account) ;
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
    public void updateAccountByAccountNumber(String accountNumber, UpdateAccountRequest updateAccountRequest) {
        int updated = accountRepository.disableAccountByAccountNumber(accountNumber);
        if (updated == 0) {
            throw new EntityNotFoundException("Account not found with accountNumber: " + accountNumber);
        }
    }
}
