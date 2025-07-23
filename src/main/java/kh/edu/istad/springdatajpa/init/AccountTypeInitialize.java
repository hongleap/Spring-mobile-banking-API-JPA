package kh.edu.istad.springdatajpa.init;

import jakarta.annotation.PostConstruct;
import kh.edu.istad.springdatajpa.domain.AccountType;
import kh.edu.istad.springdatajpa.repository.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountTypeInitialize {
    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    public void init() {
        if (accountTypeRepository.count() == 0) {
            AccountType payroll = new AccountType();
            payroll.setType("PAYROLL");

            AccountType saving = new AccountType();
            saving.setType("SAVING");

            AccountType junior = new AccountType();
            junior.setType("JUNIOR");

            accountTypeRepository.saveAll(List.of(payroll, saving, junior));
        }
    }
}
