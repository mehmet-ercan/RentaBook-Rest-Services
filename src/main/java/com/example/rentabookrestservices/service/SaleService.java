package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.OrderBookItems;
import com.example.rentabookrestservices.domain.Sale;
import com.example.rentabookrestservices.exception.ResourceNotFoundException;
import com.example.rentabookrestservices.repository.SaleBookItemsRepository;
import com.example.rentabookrestservices.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleBookItemsRepository saleBookItemsRepository;

    public SaleService(SaleRepository saleRepository, SaleBookItemsRepository saleBookItemsRepository1) {
        this.saleRepository = saleRepository;
        this.saleBookItemsRepository = saleBookItemsRepository1;
    }

    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> saleList = saleRepository.findAll();

        if (saleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(saleList, HttpStatus.OK);
    }

    public ResponseEntity<Sale> getSaleByOperationNumber(String operationNumber) {
        if (!saleRepository.existsByOperationNumber(operationNumber)) {
            throw new ResourceNotFoundException(operationNumber);
        }

        Sale sale = saleRepository.findByOperationNumber(operationNumber);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    public ResponseEntity<Sale> createSale(Sale sale) {
        List<OrderBookItems> orderBookItems = sale.getOrderBookItems();
        saleBookItemsRepository.saveAll(orderBookItems);

        return new ResponseEntity<>(saleRepository.save(sale), HttpStatus.CREATED);
    }
}
