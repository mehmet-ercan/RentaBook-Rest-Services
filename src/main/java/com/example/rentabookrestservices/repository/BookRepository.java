package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByIsbn(String isbnNumber);

}
