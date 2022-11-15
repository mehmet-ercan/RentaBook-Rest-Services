package com.example.rentabookrestservices.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BookSpecification {

    private @Id
    @GeneratedValue Long id;
    private String isbn;
    private float price;
    private LocalDate startDate;
    private LocalDate endDate;

    public BookSpecification() {
    }

    public BookSpecification(String isbn, float price, LocalDate startDate, LocalDate endDate) {
        this.isbn = isbn;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
