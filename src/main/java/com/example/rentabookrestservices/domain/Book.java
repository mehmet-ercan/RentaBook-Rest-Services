package com.example.rentabookrestservices.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Book {

    private @Id @GeneratedValue Long id;
    private String isbn;
    private String name;
    private String author;
    private int publishYear;
    private int pages;
    private String bookSpecification;

    public Book() {
    }

    public Book(String isbn, String name, String author, int publishYear, int pages, String bookSpecification) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
        this.pages = pages;
        this.bookSpecification = bookSpecification;
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

    public String getBookSpecification() {
        return bookSpecification;
    }

    public void setBookSpecification(String bookSpecification) {
        this.bookSpecification = bookSpecification;
    }

}
