package com.example.rentabookrestservices.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ElementCollection
    private Map<Book, Integer> bookIntegerMap = new HashMap<>();
    private int customerId;
    private LocalDateTime operationDateTime;
    private String operationNumber;
    private float total;

    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Book, Integer> getBookIntegerMap() {
        return bookIntegerMap;
    }

    public void setBookIntegerMap(Map<Book, Integer> bookIntegerMap) {
        this.bookIntegerMap = bookIntegerMap;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(LocalDateTime operationDateTime) {
        this.operationDateTime = operationDateTime;
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