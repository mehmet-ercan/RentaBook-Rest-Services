package com.example.rentabookrestservices.dto;

import com.example.rentabookrestservices.model.OrderBookItems;

import java.util.List;

public class RentCreateDto extends SaleCreateDto {
    public RentCreateDto(List<OrderBookItems> orderBookItems, int customerId, float total) {
        super(orderBookItems, customerId, total);
    }
}
