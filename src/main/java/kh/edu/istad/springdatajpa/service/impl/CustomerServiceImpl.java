package kh.edu.istad.springdatajpa.service.impl;
import kh.edu.istad.springdatajpa.domain.Customer;
import kh.edu.istad.springdatajpa.dto.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.CustomerResponse;
import kh.edu.istad.springdatajpa.repository.CustomerRepository;
import kh.edu.istad.springdatajpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {

        return customerRepository
                .findByPhoneNumber(phoneNumber)
                .map(customer -> CustomerResponse.builder()
                        .fullName(customer.getFullName())
                        .gender(customer.getGender())
                        .email(customer.getEmail())
                        .build())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Phone Number  Not Found")) ;
    }

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {

        // validate email
        if (customerRepository.existsByEmail(createCustomerRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        // validate phone number
        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }

        Customer customer = new Customer();
        customer.setFullName(createCustomerRequest.fullName());
        customer.setGender(createCustomerRequest.gender());
        customer.setEmail(createCustomerRequest.email());
        customer.setPhoneNumber(createCustomerRequest.phoneNumber());
        customer.setRemark(createCustomerRequest.remark());
        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());

        log.info("Customer before save: {}", customer.getId());

        customer = customerRepository.save(customer);

        log.info("Customer before save: {}", customer.getId());

        return CustomerResponse.builder()
                .fullName(customer.getFullName())
                .gender(customer.getGender())
                .email(customer.getEmail())
                .build();
    }
}
