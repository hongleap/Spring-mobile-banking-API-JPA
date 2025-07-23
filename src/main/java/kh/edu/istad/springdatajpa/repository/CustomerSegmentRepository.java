package kh.edu.istad.springdatajpa.repository;

import kh.edu.istad.springdatajpa.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, Integer> {

    Optional<CustomerSegment> findByCustomerSegment(String customerSegment);
}

