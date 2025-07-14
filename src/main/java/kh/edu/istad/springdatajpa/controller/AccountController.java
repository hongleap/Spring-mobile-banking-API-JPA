package kh.edu.istad.springdatajpa.controller;

import jakarta.validation.Valid;
import kh.edu.istad.springdatajpa.dto.AccountResponse;
import kh.edu.istad.springdatajpa.dto.CreateAccountRequest;
import kh.edu.istad.springdatajpa.dto.UpdateAccountRequest;
import kh.edu.istad.springdatajpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(createAccountRequest));
    }

    @GetMapping
    public List<AccountResponse> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse findAccountByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findAccountByAccountNumber(accountNumber);
    }

    @GetMapping("/{customerId}")
    public List<AccountResponse> findAccountByCustomerId(@PathVariable Integer customerId) {
        return accountService.findAccountByCustomerId(customerId);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccountByAccountNumber(@PathVariable String accountNumber) {
        accountService.deleteAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> deleteAccountByCustomerId(@PathVariable Integer customerId) {
        accountService.deleteAccountByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{accountNumber}")
    public void updateAccountByAccountNumber(@PathVariable  String accountNumber,
                                             @RequestBody UpdateAccountRequest updateAccountRequest) {
        accountService.updateAccountByAccountNumber(accountNumber,updateAccountRequest);
    }

}
