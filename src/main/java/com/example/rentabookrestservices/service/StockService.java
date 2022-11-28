package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.domain.Stock;
import com.example.rentabookrestservices.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public List<Stock> getStockByBookId(Long bookId) {
        return stockRepository.findStockByBook_Id(bookId);
    }

    public Stock createStock(Stock stock) {
        Book book = stock.getBook();
        boolean existStock = stockRepository.existsByBook_Id(book.getId());

        if (existStock) {
            List<Stock> stockList = stockRepository.findStockByBook_Id(book.getId());

            if (stockList.size() > 1) {

            }

            stockList.get(0).setQuantity(stockList.get(0).getQuantity() + stock.getQuantity());
            return updateStock(stockList.get(0));
        }

        Stock createdStock = stockRepository.save(stock);
        return createdStock;
    }

    public Stock updateStock(Stock newStock) {
        return stockRepository.save(newStock);
    }
}
