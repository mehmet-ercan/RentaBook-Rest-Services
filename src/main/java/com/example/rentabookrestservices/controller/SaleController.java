package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Sale;
import com.example.rentabookrestservices.dto.SaleCreateDto;
import com.example.rentabookrestservices.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> saleList = saleService.getAllSales();
        if (saleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(saleList, HttpStatus.OK);
    }

    @GetMapping("/sales/{operationNumber}")
    public ResponseEntity<Sale> getSaleBySaleNumber(@PathVariable("operationNumber") String operationNumber) {
        boolean isExist = saleService.isExistSale(operationNumber);

        if (isExist) {
            Sale sale = saleService.getSaleByOperationNumber(operationNumber);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/sales")
    public ResponseEntity<Sale> createSale(@RequestBody SaleCreateDto saleCreateDto) {
        Sale sale = saleService.createSale(saleCreateDto);

        if (sale.getId() != null) {
            return new ResponseEntity<>(sale, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/sales/{operationNumber}")
    public ResponseEntity<HttpStatus> deleteSale(@PathVariable("operationNumber") String operationNumber) {
        saleService.deleteSaleByOperationNumber(operationNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
