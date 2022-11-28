package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.OrderBookItems;
import com.example.rentabookrestservices.domain.Rent;
import com.example.rentabookrestservices.repository.OrderBookItemsRepository;
import com.example.rentabookrestservices.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final OrderBookItemsRepository orderBookItemsRepository;

    public RentService(RentRepository rentRepository, OrderBookItemsRepository orderBookItemsRepository) {
        this.rentRepository = rentRepository;
        this.orderBookItemsRepository = orderBookItemsRepository;
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public Rent getRent(String operationNumber) {
        return rentRepository.findByOperationNumber(operationNumber);
    }

    public Rent createRent(Rent rent) {
        List<OrderBookItems> orderBookItems = rent.getOrderBookItems();
        orderBookItemsRepository.saveAll(orderBookItems);
        return rentRepository.save(rent);
    }

    public void deleteRentByOperationNumber(String operationNumber) {
        rentRepository.deleteByOperationNumber(operationNumber);
    }

    public boolean isExistRent(String operationNumber) {
        return rentRepository.existsByOperationNumber(operationNumber);
    }
}
