package kh.edu.istad.springdatajpa.mapper;

import kh.edu.istad.springdatajpa.domain.Account;
import kh.edu.istad.springdatajpa.dto.account.AccountResponse;
import kh.edu.istad.springdatajpa.dto.account.CreateAccountRequest;
import kh.edu.istad.springdatajpa.dto.account.UpdateAccountRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

    AccountResponse fromAccount(Account account);

    @Mapping(target = "actCurrency", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    Account toAccount(CreateAccountRequest createAccountRequest);

//    Account fromCreateRequestToAccount(CreateAccountRequest createAccountRequest);
}
