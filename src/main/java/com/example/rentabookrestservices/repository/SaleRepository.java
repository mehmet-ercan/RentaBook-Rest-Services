package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Sale findByOperationNumber(String operationNumber);
    boolean existsByOperationNumber(String operationNumber);

    @Transactional
    void deleteByOperationNumber(String operationNumber);

}
