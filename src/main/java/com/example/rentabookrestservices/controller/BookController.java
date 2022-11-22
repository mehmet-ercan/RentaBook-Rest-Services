package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody BookCreateDto bookCreateDto) {
        return bookService.createBook(bookCreateDto);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }


}
