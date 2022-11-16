package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Customer;
import com.example.rentabookrestservices.exception.CustomerNotFoundException;
import com.example.rentabookrestservices.repository.CustomerRepository;
import com.example.rentabookrestservices.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }


}
