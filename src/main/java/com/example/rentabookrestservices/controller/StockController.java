package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Stock;
import com.example.rentabookrestservices.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/stocks/{bookId}")
    public ResponseEntity<Stock> createStock(@PathVariable("bookId") Long bookId,
                                             @RequestBody Stock stock) {

        return stockService.createStock(bookId, stock);
    }

}
