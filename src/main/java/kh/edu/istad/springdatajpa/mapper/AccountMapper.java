package kh.edu.istad.springdatajpa.mapper;

import kh.edu.istad.springdatajpa.domain.Account;
import kh.edu.istad.springdatajpa.dto.AccountResponse;
import kh.edu.istad.springdatajpa.dto.UpdateAccountRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

    AccountResponse fromAccount(Account account);

    Account toAccount(AccountResponse accountResponse);
}
