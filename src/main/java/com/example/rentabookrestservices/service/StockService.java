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
            //TODO stok verisi alındığında birden fazla kayıt gelirse hata vermesi sağlanmalı
            List<Stock> stockList = stockRepository.findStockByBook_Id(book.getId());
            stockList.get(0).setQuantity(stockList.get(0).getQuantity() + stock.getQuantity());
            return updateStock(stockList.get(0));
        }

        return stockRepository.save(stock);
    }

    public Stock updateStock(Stock newStock) {
        return stockRepository.save(newStock);
    }

    public boolean increaseStock(Book _book, int quantity){

        List<Stock> stockList = stockRepository.findStockByBook_Id(_book.getId());

        if (stockList.size() == 1) {
            stockList.get(0).setQuantity(stockList.get(0).getQuantity() + quantity);
            stockRepository.save(stockList.get(0));

            return true;
        }

        return false;
    }

    public boolean decreaseStock(Book _book, int quantity){

        List<Stock> stockList = stockRepository.findStockByBook_Id(_book.getId());

        if (stockList.size() == 1) {
            stockList.get(0).setQuantity(stockList.get(0).getQuantity() - quantity);
            stockRepository.save(stockList.get(0));

            return true;
        }

        return false;
    }

}
