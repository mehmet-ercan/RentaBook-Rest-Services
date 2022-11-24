package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.OrderBookItems;
import com.example.rentabookrestservices.domain.Rent;
import com.example.rentabookrestservices.repository.OrderBookItemsRepository;
import com.example.rentabookrestservices.repository.RentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<List<Rent>> getAllRents() {
        List<Rent> rentList = rentRepository.findAll();

        if (rentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rentList, HttpStatus.OK);
    }

    public ResponseEntity<Rent> getRent(String operationNumber) {
        Rent rent = rentRepository.findByOperationNumber(operationNumber);
        return new ResponseEntity<Rent>(rent, HttpStatus.OK);
    }

    public ResponseEntity<Rent> createRent(Rent rent) {
        List<OrderBookItems> orderBookItems = rent.getOrderBookItems();
        orderBookItemsRepository.saveAll(orderBookItems);
        return new ResponseEntity<>(rentRepository.save(rent), HttpStatus.CREATED);
    }


    public ResponseEntity<HttpStatus> deleteRentByOperationNumber(String operationNumber) {
        rentRepository.deleteByOperationNumber(operationNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public boolean isExistRent(String operationNumber) {
        return rentRepository.existsByOperationNumber(operationNumber);
    }
}
