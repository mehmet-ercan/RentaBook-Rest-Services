package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.domain.Stock;
import com.example.rentabookrestservices.exception.StockNotFoundException;
import com.example.rentabookrestservices.repository.BookRepository;
import com.example.rentabookrestservices.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final BookRepository bookRepository;


    public StockService(StockRepository stockRepository, BookRepository bookRepository) {
        this.stockRepository = stockRepository;
        this.bookRepository = bookRepository;
    }

    public Stock findStockById(long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException(stockId));
    }

    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stockList = stockRepository.findAll();

        if (stockList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }

    public ResponseEntity<Stock> getStockByBookId(Long bookId) {

        List<Stock> stockList = stockRepository.findStockByBook_Id(bookId);
        if (stockList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (stockList.size() == 1) {
            return new ResponseEntity<>(stockList.get(0), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Stock> createStock(Stock stock) {
        Book book = stock.getBook();
        boolean existStock = stockRepository.existsByBook_Id(book.getId());

        if (existStock) {
            List<Stock> stockList = stockRepository.findStockByBook_Id(book.getId());
            stockList.get(0).setQuantity(stockList.get(0).getQuantity() + stock.getQuantity());
            return updateStock(stockList.get(0));
        }

        Stock createdStock = stockRepository.save(stock);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

    public ResponseEntity<Stock> updateStock(Stock newStock) {
        Stock stock = stockRepository.save(newStock);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}
