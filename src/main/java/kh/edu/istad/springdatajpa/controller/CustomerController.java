package kh.edu.istad.springdatajpa.controller;

import jakarta.validation.Valid;
import kh.edu.istad.springdatajpa.dto.customer.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.customer.CustomerResponse;
import kh.edu.istad.springdatajpa.dto.customer.UpdateCustomerRequest;
import kh.edu.istad.springdatajpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{phoneNumber}")
    public void disableByPhoneNumber(@PathVariable String phoneNumber) {
        customerService.disableByPhoneNumber(phoneNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{phoneNumber}")
    public void deleteByPhoneNumber(@PathVariable String phoneNumber) {
        customerService.deleteByPhoneNumber(phoneNumber);
    }

   @PatchMapping("/{phoneNumber}")
   public CustomerResponse updateByPhoneNumber(@PathVariable String phoneNumber,
                                               @RequestBody UpdateCustomerRequest updateCustomerRequest) {
       return customerService.updateByPhoneNumber(phoneNumber, updateCustomerRequest);
   }

    @GetMapping("/{phoneNumber}")
    public CustomerResponse findByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.findByPhoneNumber(phoneNumber);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerResponse createNew(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createNew(createCustomerRequest);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAllCustomers();
    }
}
