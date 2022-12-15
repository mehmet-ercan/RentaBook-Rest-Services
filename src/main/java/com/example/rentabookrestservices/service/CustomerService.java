package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.exception.CustomerNotFoundException;
import com.example.rentabookrestservices.model.Customer;
import com.example.rentabookrestservices.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerList = customerRepository.findAll();

        return customerList;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return customer;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
