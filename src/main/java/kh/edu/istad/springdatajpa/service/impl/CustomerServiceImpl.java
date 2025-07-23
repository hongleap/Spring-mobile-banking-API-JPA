package kh.edu.istad.springdatajpa.service.impl;
import kh.edu.istad.springdatajpa.domain.Customer;
import kh.edu.istad.springdatajpa.domain.CustomerSegment;
import kh.edu.istad.springdatajpa.domain.KYC;
import kh.edu.istad.springdatajpa.dto.customer.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.customer.CustomerResponse;
import kh.edu.istad.springdatajpa.dto.customer.UpdateCustomerRequest;
import kh.edu.istad.springdatajpa.mapper.CustomerMapper;
import kh.edu.istad.springdatajpa.repository.CustomerRepository;
import kh.edu.istad.springdatajpa.repository.CustomerSegmentRepository;
import kh.edu.istad.springdatajpa.repository.KYCRepository;
import kh.edu.istad.springdatajpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerSegmentRepository customerSegmentRepository;
    private final KYCRepository kycRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {
        if (!customerRepository.isExistsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found");
        }
        customerRepository.disableByPhoneNumber(phoneNumber);
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found"));

        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found"));

        customerMapper.toCustomerPartially(updateCustomerRequest, customer);

        customer = customerRepository.save(customer);

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Phone Number  Not Found")) ;
    }

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {

        // validate national card id
        if (kycRepository.existsByNationalCardId(createCustomerRequest.nationalCardId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer National Card Id already exists");
        }

        // validate email
        if (customerRepository.existsByEmail(createCustomerRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        // validate phone number
        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }

        // validate customer segment
        CustomerSegment customerSegment = customerSegmentRepository
                .findByCustomerSegment(createCustomerRequest.customerSegment())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,
                        "Customer segment not found!"));

        // map from dto
        Customer customer = customerMapper.toCustomer(createCustomerRequest);

        // prepared KYC of customer
        KYC kyc = new KYC();
        kyc.setNationalCardId(createCustomerRequest.nationalCardId());
        kyc.setIsVerified(false);
        kyc.setIsDeleted(false);
        kyc.setCustomer(customer);

        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());
        customer.setCustomerSegment(customerSegment);
        customer.setKyc(kyc);

        log.info("Customer before save: {}", customer.getId());

        customer = customerRepository.save(customer);

        log.info("Customer before save: {}", customer.getId());

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = customerRepository.findAllByIsDeletedFalse();
        return customers
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }
}
