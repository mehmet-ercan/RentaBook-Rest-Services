package com.example.rentabookrestservices.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToMany
    private List<SaleBookItems> saleBookItems = new ArrayList<>();

    LocalDateTime operationDateTime;
    int customerId;
    String operationNumber;
    float total;

    public Sale() {
    }

    public Sale(List<SaleBookItems> saleBookItems, LocalDateTime operationDateTime, int customerId, String operationNumber, float total) {
        this.saleBookItems = saleBookItems;
        this.operationDateTime = operationDateTime;
        this.customerId = customerId;
        this.operationNumber = operationNumber;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SaleBookItems> getSaleBookItems() {
        return saleBookItems;
    }

    public void setSaleBookItems(List<SaleBookItems> saleBookItems) {
        this.saleBookItems = saleBookItems;
    }

    public LocalDateTime getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(LocalDateTime operationDateTime) {
        this.operationDateTime = operationDateTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}