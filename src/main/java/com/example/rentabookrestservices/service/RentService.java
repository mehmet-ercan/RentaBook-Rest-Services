package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.OrderBookItems;
import com.example.rentabookrestservices.domain.Rent;
import com.example.rentabookrestservices.repository.OrderBookItemsRepository;
import com.example.rentabookrestservices.repository.RentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentService {
    Logger logger = LoggerFactory.getLogger(RentService.class);
    private final RentRepository rentRepository;
    private final OrderBookItemsRepository orderBookItemsRepository;

    public RentService(RentRepository rentRepository, OrderBookItemsRepository orderBookItemsRepository) {
        this.rentRepository = rentRepository;
        this.orderBookItemsRepository = orderBookItemsRepository;
        System.out.println(generateOperationNumber());
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

    public String generateOperationNumber() {
        LocalDateTime now = LocalDateTime.now();
        String operationNumber = "R" + now.getDayOfMonth() + now.getMonthValue()
                + now.getYear() + now.getHour() + now.getMinute() + now.getSecond();
        logger.info(operationNumber);
        return operationNumber;
    }
}
