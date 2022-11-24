package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.BookSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentabookrestservices.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {


}
