package kh.edu.istad.springdatajpa.service;

import kh.edu.istad.springdatajpa.dto.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse findByPhoneNumber(String phoneNumber);

    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);
}
