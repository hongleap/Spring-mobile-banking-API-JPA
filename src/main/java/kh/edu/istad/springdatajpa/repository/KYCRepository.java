package kh.edu.istad.springdatajpa.repository;

import kh.edu.istad.springdatajpa.domain.KYC;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KYCRepository extends CrudRepository<KYC, Integer> {
    boolean existsByNationalCardId(String nationalCardId);
    Optional<KYC> findByCustomerId(Integer customerId);
}
