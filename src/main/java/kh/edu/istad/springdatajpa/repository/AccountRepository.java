package kh.edu.istad.springdatajpa.repository;

import jakarta.transaction.Transactional;
import kh.edu.istad.springdatajpa.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Integer> {

    boolean existsByAccountNumber(String accountNumber);

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findAccountByCustomerId(Integer customerId);

    @Modifying
    @Query("DELETE FROM Account a WHERE a.customer = :customerId")
    void deleteAccountByCustomerId(@Param("customerId") Integer customerId);

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.isDeleted = true WHERE a.accountNumber = :accountNumber")
    int disableAccountByAccountNumber(@Param("accountNumber") String accountNumber);
}
