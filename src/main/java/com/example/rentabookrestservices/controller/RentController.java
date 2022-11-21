package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Rent;
import com.example.rentabookrestservices.service.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/rents")
    public ResponseEntity<List<Rent>> getAllRents() {
        return rentService.getAllRents();
    }

    @GetMapping("/rents/{operationNumber}")
    public ResponseEntity<Rent> getRent(@PathVariable("operationNumber") String operationNumber) {
        return rentService.getRent(operationNumber);
    }


    @PostMapping("/rents")
    public ResponseEntity<Rent> createRent(@RequestBody Rent rent) {
        return rentService.createRent(rent);
    }

    @DeleteMapping("/rents/{operationNumber}")
    public ResponseEntity<HttpStatus> deleteRent(@PathVariable("operationNumber") String operationNumber) {
        boolean isExistRent = rentService.isExistRent(operationNumber);

        if (isExistRent) {
            return rentService.deleteRentByOperationNumber(operationNumber);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
