package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);

    Optional<Customer> findByEmail(String email);

    List<Customer> findByPhone(String phone);
    // Define custom query methods if needed
}
