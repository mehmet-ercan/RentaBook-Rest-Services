package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.OrderBookItems;
import com.example.rentabookrestservices.domain.Rent;
import com.example.rentabookrestservices.repository.RentBookItemsRepository;
import com.example.rentabookrestservices.repository.RentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final RentBookItemsRepository rentBookItemsRepository;

    public RentService(RentRepository rentRepository, RentBookItemsRepository rentBookItemsRepository) {
        this.rentRepository = rentRepository;
        this.rentBookItemsRepository = rentBookItemsRepository;
    }

    public ResponseEntity<List<Rent>> getAllRent() {
        List<Rent> rentList = rentRepository.findAll();

        if (rentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rentList, HttpStatus.OK);
    }

    public ResponseEntity<Rent> createRent(Rent rent) {
        List<OrderBookItems> orderBookItems = rent.getOrderBookItems();
        rentBookItemsRepository.saveAll(orderBookItems);
        return new ResponseEntity<>(rentRepository.save(rent), HttpStatus.CREATED);
    }
}
