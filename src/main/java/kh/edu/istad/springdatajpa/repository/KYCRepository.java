package kh.edu.istad.springdatajpa.repository;

import kh.edu.istad.springdatajpa.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository extends JpaRepository<KYC, Integer> {
}
