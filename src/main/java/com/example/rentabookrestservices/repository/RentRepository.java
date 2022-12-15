package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface RentRepository extends JpaRepository<Rent, Long> {

    boolean existsByOperationNumber(String operationNumber);

    @Transactional
    void deleteByOperationNumber(String operationNumber);

    Rent findByOperationNumber(String operationNumber);
}
