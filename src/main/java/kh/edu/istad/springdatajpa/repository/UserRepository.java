package kh.edu.istad.springdatajpa.repository;

import kh.edu.istad.springdatajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
