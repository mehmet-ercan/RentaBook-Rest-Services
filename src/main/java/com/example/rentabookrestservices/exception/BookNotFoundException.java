package com.example.rentabookrestservices.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(id + " numaralı kitap bulunamamıştır.");
    }
}
