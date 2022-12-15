package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.dto.SaleCreateDto;
import com.example.rentabookrestservices.mapper.SaleMapper;
import com.example.rentabookrestservices.model.OrderBookItems;
import com.example.rentabookrestservices.model.Sale;
import com.example.rentabookrestservices.repository.OrderBookItemsRepository;
import com.example.rentabookrestservices.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final OrderBookItemsRepository orderBookItemsRepository;
    private final StockService stockService;

    public SaleService(SaleRepository saleRepository, OrderBookItemsRepository saleBookItemsRepository1, StockService stockService) {
        this.saleRepository = saleRepository;
        this.orderBookItemsRepository = saleBookItemsRepository1;
        this.stockService = stockService;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSale(String operationNumber) {
        return saleRepository.findByOperationNumber(operationNumber);
    }

    public boolean isExistSale(String operationNumber) {
        return saleRepository.existsByOperationNumber(operationNumber);
    }

    public Sale createSale(SaleCreateDto saleCreateDto) {
        Sale sale = SaleMapper.INSTANCE.saleCreateDtoToSale(saleCreateDto);
        List<OrderBookItems> orderBookItems = sale.getOrderBookItems();

        sale.setOperationDateTime(LocalDateTime.now());
        sale.setOperationNumber(generateOperationNumber());

        boolean isSuccessful = true;
        Sale _sale = new Sale();

        for (OrderBookItems o : orderBookItems) {
            isSuccessful = stockService.decreaseStock(o.getBook(), o.getQuantity());
        }

        if (isSuccessful) {
            orderBookItemsRepository.saveAll(orderBookItems);
            _sale = saleRepository.save(sale);
        }

        return _sale;
    }

    public boolean deleteSaleByOperationNumber(String operationNumber) {

        Sale sale;
        if (isExistSale(operationNumber)) {
            sale = getSale(operationNumber);

            for (OrderBookItems o : sale.getOrderBookItems()) {
                stockService.increaseStock(o.getBook(), o.getQuantity());
            }

            saleRepository.deleteByOperationNumber(operationNumber);
            return true;
        }

        return false;

    }

    public String generateOperationNumber() {
        LocalDateTime now = LocalDateTime.now();
        String operationNumber = "S" + now.getDayOfMonth() + now.getMonthValue()
                + now.getYear() + now.getHour() + now.getMinute() + now.getSecond();
        return operationNumber;
    }

}
