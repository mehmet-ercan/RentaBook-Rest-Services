package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Rent;
import com.example.rentabookrestservices.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/rents")
    public ResponseEntity<List<Rent>> getAllRent() {
        return rentService.getAllRent();
    }

    @PostMapping("/rents")
    public ResponseEntity<Rent> createRent(@RequestBody Rent rent){
        return rentService.createRent(rent);
    }
}
