package com.example.rentabookrestservices.model;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String isbn;
    private String name;
    private String author;
    private int publishYear;
    private int pages;
    @OneToOne
    private BookPrice bookPrice;

    public Book() {
    }

    public Book(String isbn, String name, String author, int publishYear, int pages, BookPrice bookPrice) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
        this.pages = pages;
        this.bookPrice = bookPrice;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public BookPrice getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BookPrice bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "Kitabın Bilgileri" + " isbn=" + this.isbn + ", adı=" +
                this.name + ", ücreti=" + this.bookPrice.getPrice();
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
