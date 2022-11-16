package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.BookSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSpecificationRepository extends JpaRepository<BookSpecification, Long> {
    BookSpecification findByIsbn(String isbn);
}
