package com.example.rentabookrestservices.dto;

import com.example.rentabookrestservices.model.OrderBookItems;

import java.util.ArrayList;
import java.util.List;

public class SaleCreateDto {
    private List<OrderBookItems> orderBookItems = new ArrayList<>();
    private int customerId;
    private float total;

    public SaleCreateDto(List<OrderBookItems> orderBookItems, int customerId, float total) {
        this.orderBookItems = orderBookItems;
        this.customerId = customerId;
        this.total = total;
    }

    public List<OrderBookItems> getOrderBookItems() {
        return orderBookItems;
    }

    public void setOrderBookItems(List<OrderBookItems> orderBookItems) {
        this.orderBookItems = orderBookItems;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
