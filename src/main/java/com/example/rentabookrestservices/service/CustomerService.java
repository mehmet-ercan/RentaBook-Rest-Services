package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.Customer;
import com.example.rentabookrestservices.exception.CustomerNotFoundException;
import com.example.rentabookrestservices.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>(customerRepository.findAll());
        //customerList = customerRepository.findAll();

        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    public ResponseEntity<Customer> getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
