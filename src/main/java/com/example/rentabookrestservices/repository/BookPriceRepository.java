package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.BookPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPriceRepository extends JpaRepository<BookPrice, Long> {
}
