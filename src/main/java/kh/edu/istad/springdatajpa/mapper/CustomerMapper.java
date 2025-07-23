package kh.edu.istad.springdatajpa.mapper;
import kh.edu.istad.springdatajpa.domain.Customer;
import kh.edu.istad.springdatajpa.dto.customer.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.customer.CustomerResponse;
import kh.edu.istad.springdatajpa.dto.customer.UpdateCustomerRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // Partially update
    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest
    ,@MappingTarget Customer customer);

    CustomerResponse fromCustomer(Customer customer);

    @Mapping(source = "customerSegment", target = "customerSegment.customerSegment")
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

//    Customer fromcreateRequestToCustomer(CreateCustomerRequest createCustomerRequest);
}
