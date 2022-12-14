package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.model.Book;
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
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/books/q")
    public ResponseEntity<Book> getBookByIsbnNumber(@RequestParam(name = "isbn") String isbnNumber) {
        List<Book> bookList = bookService.getBookByIsbnNumber(isbnNumber);

        if (bookList.size() == 1) {
            return new ResponseEntity<>(bookList.get(0), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody BookCreateDto bookCreateDto) {
        return new ResponseEntity<>(bookService.createBook(bookCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }

}
