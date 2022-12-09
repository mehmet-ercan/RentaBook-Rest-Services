package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.OrderBookItems;
import com.example.rentabookrestservices.domain.Rent;
import com.example.rentabookrestservices.dto.RentCreateDto;
import com.example.rentabookrestservices.mapper.RentMapper;
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
    private final StockService stockService;
    private final float REFUND_PERCENT = 0.75F;

    public RentService(RentRepository rentRepository, OrderBookItemsRepository orderBookItemsRepository, StockService stockService) {
        this.rentRepository = rentRepository;
        this.orderBookItemsRepository = orderBookItemsRepository;
        this.stockService = stockService;
        System.out.println(generateOperationNumber());
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public Rent getRent(String operationNumber) {
        return rentRepository.findByOperationNumber(operationNumber);
    }

    public Rent createRent(RentCreateDto rentCreateDto) {
        Rent rent = RentMapper.INSTANCE.rentCreateDtoToRent(rentCreateDto);
        List<OrderBookItems> orderBookItems = rent.getOrderBookItems();

        rent.setOperationDateTime(LocalDateTime.now());
        rent.setOperationNumber(generateOperationNumber());

        rent.setRefundDate(rent.getOperationDateTime().plusDays(14));
        rent.setRefund(rent.getTotal() * REFUND_PERCENT);

        boolean isSuccessful = true;
        Rent _rent = new Rent();

        for (OrderBookItems o : orderBookItems) {
            isSuccessful = stockService.decreaseStock(o.getBook(), o.getQuantity());
        }

        if (isSuccessful) {
            orderBookItemsRepository.saveAll(orderBookItems);
            _rent = rentRepository.save(rent);
        }

        return _rent;
    }

    public boolean deleteRentByOperationNumber(String operationNumber) {
        Rent rent;

        if (isExistRent(operationNumber)) {
            rent = getRent(operationNumber);

            for (OrderBookItems o : rent.getOrderBookItems()) {
                stockService.increaseStock(o.getBook(), o.getQuantity());
            }

            rentRepository.deleteByOperationNumber(operationNumber);
            return true;
        }

        return false;

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

    public Rent refundRent(String operationNumber) {
        Rent rent = getRent(operationNumber);
        rent.setRefundDate(LocalDateTime.now());
        rent.setRefund(calculateRefundAmount(rent));

        boolean isSuccessful = true;

        for (OrderBookItems o : rent.getOrderBookItems()) {
            isSuccessful = stockService.increaseStock(o.getBook(), o.getQuantity());
        }

        if (isSuccessful) {
            return rent;
        }

        return new Rent();

    }

    /**
     * Kitap kiralandı, okundu ve geri getirildiği zaman ne kadar geri ödeme verileceğini hesaplar
     *
     * @param rent Hesaplanacak olan geri ödeme miktarının içinde bulunduğu nesne
     * @return Geri ödeme miktarı
     */
    public float calculateRefundAmount(Rent rent) {
        float refundPercent;
        int diffDays = rent.getRefundDate().getDayOfYear() - rent.getOperationDateTime().getDayOfYear();

        if (diffDays <= 14) {
            refundPercent = REFUND_PERCENT;
        } else if (diffDays <= 24) {
            refundPercent = REFUND_PERCENT - ((diffDays - 14) * 0.05f);
        } else {
            refundPercent = 0.25f;
        }

        return rent.getTotal() * refundPercent;
    }

}
