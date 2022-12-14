package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.dto.RentCreateDto;
import com.example.rentabookrestservices.model.Rent;
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
        List<Rent> rentList = rentService.getAllRents();

        if (rentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rentList, HttpStatus.OK);
    }

    @GetMapping("/rents/{operationNumber}")
    public ResponseEntity<Rent> getRent(@PathVariable("operationNumber") String operationNumber) {
        if (rentService.isExistRent(operationNumber)) {
            Rent rent = rentService.getRent(operationNumber);
            return new ResponseEntity<>(rent, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/rents")
    public ResponseEntity<Rent> createRent(@RequestBody RentCreateDto rentCreateDto) {
        return new ResponseEntity<>(rentService.createRent(rentCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/rents/{operationNumber}")
    public ResponseEntity<HttpStatus> deleteRent(@PathVariable("operationNumber") String operationNumber) {
        boolean result = rentService.deleteRentByOperationNumber(operationNumber);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/refunds/rents/{operationNumber}")
    public ResponseEntity<Rent> refundRent(@PathVariable("operationNumber") String operationNumber) {
        if (rentService.isExistRent(operationNumber)) {
            Rent rent = rentService.refundRent(operationNumber);

            if (rent.getId() != null) {
                return new ResponseEntity<>(rent, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
