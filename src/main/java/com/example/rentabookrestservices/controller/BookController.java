package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;


    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") long id, @RequestBody Book _book) {
        return bookService.updateBook(id, _book);
    }


}
