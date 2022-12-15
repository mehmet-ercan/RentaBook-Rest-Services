package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findStockByBook_Id(Long bookId);
    boolean existsByBook_Id(long bookId);
}
