package com.example.rentabookrestservices.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cancel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Sale cancelType;
    private Float refund;
    private LocalDateTime canceledDateTime;

    public Cancel() {
    }

    public Cancel(Sale cancelType, Float refund, LocalDateTime canceledDateTime) {
        this.cancelType = cancelType;
        this.refund = refund;
        this.canceledDateTime = canceledDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sale getCancelType() {
        return cancelType;
    }

    public void setCancelType(Sale cancelType) {
        this.cancelType = cancelType;
    }

    public Float getRefund() {
        return refund;
    }

    public void setRefund(Float refund) {
        this.refund = refund;
    }

    public LocalDateTime getCanceledDateTime() {
        return canceledDateTime;
    }

    public void setCanceledDateTime(LocalDateTime canceledDateTime) {
        this.canceledDateTime = canceledDateTime;
    }
}
