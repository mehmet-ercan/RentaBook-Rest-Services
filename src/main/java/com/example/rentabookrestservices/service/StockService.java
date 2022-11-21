package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.domain.Stock;
import com.example.rentabookrestservices.exception.BookNotFoundException;
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

    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stockList = stockRepository.findAll();

        if (stockList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }

    public ResponseEntity<Stock> getStockById(Long id) {

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(id));

        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    public ResponseEntity<Stock> createStock(Long bookId, Stock stock) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));


        /*boolean isExist = stockRepository.existsByIsbn(stock.getIsbn());

        if(isExist){
            stockRepository.deleteByIsbn(stock.getIsbn());
        }
        */

        stock.setBook(book);
        Stock createdStock = stockRepository.save(stock);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

}
