package kh.edu.istad.springdatajpa.service;

import kh.edu.istad.springdatajpa.dto.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.CustomerResponse;
import kh.edu.istad.springdatajpa.dto.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    void deleteByPhoneNumber(String phoneNumber);

    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);

    CustomerResponse findByPhoneNumber(String phoneNumber);

    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);

    List<CustomerResponse> findAllCustomers();
}
