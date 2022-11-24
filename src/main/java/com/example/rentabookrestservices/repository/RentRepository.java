package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
