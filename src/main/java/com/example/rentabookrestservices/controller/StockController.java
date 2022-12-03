package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.model.Stock;
import com.example.rentabookrestservices.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stockList = stockService.getAllStocks();
        if (stockList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }

    @GetMapping("/stocks/q")
    public ResponseEntity<Stock> getStockByBookId(@RequestParam(name = "bookId") Long bookId) {
        List<Stock> stockList = stockService.getStockByBookId(bookId);

        if (stockList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (stockList.size() == 1) {
            return new ResponseEntity<>(stockList.get(0), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/stocks")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        return new ResponseEntity<>(stockService.createStock(stock), HttpStatus.CREATED);
    }
}
