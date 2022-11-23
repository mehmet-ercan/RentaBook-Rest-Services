package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Stock;
import com.example.rentabookrestservices.service.StockService;
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
        return stockService.getAllStocks();
    }

    @GetMapping("/stocks/q")
    public ResponseEntity<Stock> getStockByBookId(@RequestParam(name = "bookId") long bookId) {
        return stockService.getStockByBookId(bookId);
    }

    @PostMapping("/stocks")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

}
