package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.OrderBookItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBookItemsRepository extends JpaRepository<OrderBookItems, Long> {


}
