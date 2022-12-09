package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.OrderBookItems;
import com.example.rentabookrestservices.domain.Sale;
import com.example.rentabookrestservices.repository.OrderBookItemsRepository;
import com.example.rentabookrestservices.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final OrderBookItemsRepository orderBookItemsRepository;

    public SaleService(SaleRepository saleRepository, OrderBookItemsRepository saleBookItemsRepository1) {
        this.saleRepository = saleRepository;
        this.orderBookItemsRepository = saleBookItemsRepository1;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleByOperationNumber(String operationNumber) {
        return saleRepository.findByOperationNumber(operationNumber);
    }

    public boolean isExistSale(String operationNumber) {
        return saleRepository.existsByOperationNumber(operationNumber);
    }

    public Sale createSale(Sale sale) {
        List<OrderBookItems> orderBookItems = sale.getOrderBookItems();
        orderBookItemsRepository.saveAll(orderBookItems);
        return saleRepository.save(sale);
    }

    public void deleteSaleByOperationNumber(String operationNumber) {
        saleRepository.deleteByOperationNumber(operationNumber);
    }
}
