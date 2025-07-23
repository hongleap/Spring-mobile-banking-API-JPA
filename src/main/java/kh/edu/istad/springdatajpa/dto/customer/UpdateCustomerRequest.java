package kh.edu.istad.springdatajpa.dto.customer;

public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}
