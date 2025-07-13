package kh.edu.istad.springdatajpa.repository;

import kh.edu.istad.springdatajpa.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
