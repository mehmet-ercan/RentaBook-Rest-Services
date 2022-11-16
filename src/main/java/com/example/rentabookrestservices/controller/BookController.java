package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.domain.BookSpecification;
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
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        bookSpecificationRepository.save(book.getBookSpecification());
        return bookRepository.save(book);
    }

    //TODO put işlemi denenecek
    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") long id, @RequestBody Book _book) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        BookSpecification bookSpecification = bookSpecificationRepository.findByIsbn(_book.getIsbn());

        book.setIsbn(_book.getIsbn());
        book.setName(_book.getName());
        book.setPages(_book.getPages());
        book.setPublishYear(_book.getPublishYear());

        bookSpecification.setIsbn(_book.getIsbn());
        bookSpecification.setPrice(_book.getBookSpecification().getPrice());
        bookSpecification.setStartDate(_book.getBookSpecification().getStartDate());
        bookSpecification.setEndDate(_book.getBookSpecification().getEndDate());

        bookSpecificationRepository.save(bookSpecification);
        return bookRepository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
