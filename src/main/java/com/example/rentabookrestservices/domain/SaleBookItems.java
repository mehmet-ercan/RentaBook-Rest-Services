package com.example.rentabookrestservices.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SaleBookItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Book book;
    private Integer quantity;

    public SaleBookItems() {
    }

    public SaleBookItems(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleBookItems that = (SaleBookItems) o;
        return Objects.equals(id, that.id) && Objects.equals(book, that.book) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, quantity);
    }

    @Override
    public String toString() {
        return "SaleBookItems{" +
                "id=" + id +
                ", book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
