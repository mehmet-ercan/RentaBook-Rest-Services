package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
