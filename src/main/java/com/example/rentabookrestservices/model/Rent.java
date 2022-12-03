package com.example.rentabookrestservices.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Rent extends Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime refundDate;
    private Float refund;

    public Rent() {
    }

    public Rent(List<OrderBookItems> orderBookItems, LocalDateTime operationDateTime, int customerId, String operationNumber, float total, LocalDateTime refundDate, Float refund) {
        super(orderBookItems, operationDateTime, customerId, operationNumber, total);
        this.refundDate = refundDate;
        this.refund = refund;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDateTime refundDate) {
        this.refundDate = refundDate;
    }

    public Float getRefund() {
        return refund;
    }

    public void setRefund(Float refund) {
        this.refund = refund;
    }
}
