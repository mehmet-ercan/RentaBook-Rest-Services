package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.model.OrderBookItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBookItemsRepository extends JpaRepository<OrderBookItems, Long> {


}
