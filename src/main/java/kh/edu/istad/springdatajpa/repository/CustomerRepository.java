package kh.edu.istad.springdatajpa.repository;

import kh.edu.istad.springdatajpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByIsDeletedFalse();

    // JPQL
    @Modifying
    @Query(value = """
       UPDATE Customer c SET c.isDeleted = TRUE
              WHERE c.phoneNumber = :phoneNumber
       """)
    void disableByPhoneNumber(String phoneNumber);

    @Query(value = """
        SELECT EXISTS (SELECT c.id 
            FROM Customer c 
                WHERE c.phoneNumber = ?1 )
    """)
    boolean isExistsByPhoneNumber(String phoneNumber);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
