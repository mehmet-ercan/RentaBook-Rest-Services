package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.exception.BookNotFoundException;
import com.example.rentabookrestservices.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookRepository repository;

    BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    Book getBook(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
