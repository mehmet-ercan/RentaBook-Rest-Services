package com.example.rentabookrestservices.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(id + " numaralı müşteri bulunamamıştır.");
    }
}
