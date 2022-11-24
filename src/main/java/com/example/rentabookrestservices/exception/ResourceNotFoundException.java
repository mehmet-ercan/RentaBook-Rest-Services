package com.example.rentabookrestservices.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String id) {
        super(id + "numaralı kayıt bulunamamıştır.");
    }
}
