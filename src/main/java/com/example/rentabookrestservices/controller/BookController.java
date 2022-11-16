package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.exception.BookNotFoundException;
import com.example.rentabookrestservices.repository.BookRepository;
import com.example.rentabookrestservices.repository.BookSpecificationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookRepository bookRepository;
    private final BookSpecificationRepository bookSpecificationRepository;

    BookController(BookRepository bookRepository, BookSpecificationRepository bookSpecificationRepository) {
        this.bookRepository = bookRepository;
        this.bookSpecificationRepository = bookSpecificationRepository;
    }

    @GetMapping("/books")
    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    Book getBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        bookSpecificationRepository.save(newBook.getBookSpecification());
        return bookRepository.save(newBook);
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
