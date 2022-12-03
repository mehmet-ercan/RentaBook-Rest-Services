package com.example.rentabookrestservices.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderBookItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Book book;
    private Integer quantity;

    public OrderBookItems() {
    }

    public OrderBookItems(Book book, Integer quantity) {
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
        OrderBookItems that = (OrderBookItems) o;
        return Objects.equals(id, that.id) && Objects.equals(book, that.book) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, quantity);
    }

    @Override
    public String toString() {
        return "OrderBookItems{" +
                "id=" + id +
                ", book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
