package kh.edu.istad.springdatajpa.controller;

import jakarta.validation.Valid;
import kh.edu.istad.springdatajpa.dto.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.CustomerResponse;
import kh.edu.istad.springdatajpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{phoneNumber}")
    public CustomerResponse findeByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.findByPhoneNumber(phoneNumber);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerResponse createNew(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createNew(createCustomerRequest);
    }
}
