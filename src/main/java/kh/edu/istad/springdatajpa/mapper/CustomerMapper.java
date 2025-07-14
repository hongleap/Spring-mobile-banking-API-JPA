package kh.edu.istad.springdatajpa.mapper;
import kh.edu.istad.springdatajpa.domain.Customer;
import kh.edu.istad.springdatajpa.dto.CreateCustomerRequest;
import kh.edu.istad.springdatajpa.dto.CustomerResponse;
import kh.edu.istad.springdatajpa.dto.UpdateCustomerRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // Partially update
    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest
    ,@MappingTarget Customer customer);

    CustomerResponse fromCustomer(Customer customer);

    Customer toCustomer(CreateCustomerRequest createCustomerRequest);
}
