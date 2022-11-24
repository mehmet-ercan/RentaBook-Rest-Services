package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.domain.SaleBookItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleBookItemsRepository extends JpaRepository<SaleBookItems, Long> {


}
