package kh.edu.istad.springdatajpa.service;

import kh.edu.istad.springdatajpa.dto.customer.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.customer.CustomerResponse;
import kh.edu.istad.springdatajpa.dto.customer.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    void disableByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);

    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);

    CustomerResponse findByPhoneNumber(String phoneNumber);

    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);

    List<CustomerResponse> findAllCustomers();
}
