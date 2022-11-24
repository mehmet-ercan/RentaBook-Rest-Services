package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {


}
