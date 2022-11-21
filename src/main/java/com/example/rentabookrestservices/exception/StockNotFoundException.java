package com.example.rentabookrestservices.exception;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(Long id){
        super(id + "numaralı stok bulunamamıştır.");
    }
}
