package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
