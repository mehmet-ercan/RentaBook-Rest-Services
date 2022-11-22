package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.domain.BookSpecification;
import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.exception.BookNotFoundException;
import com.example.rentabookrestservices.mapper.BookMapper;
import com.example.rentabookrestservices.repository.BookRepository;
import com.example.rentabookrestservices.repository.BookSpecificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookSpecificationRepository bookSpecificationRepository;

    public BookService(BookRepository bookRepository, BookSpecificationRepository bookSpecificationRepository) {
        this.bookRepository = bookRepository;
        this.bookSpecificationRepository = bookSpecificationRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public ResponseEntity<Book> createBook(BookCreateDto bookCreateDto) {
        Book book = BookMapper.INSTANCE.bookCreateDtoToBook(bookCreateDto);
        BookSpecification bookSpecification = BookMapper.INSTANCE.bookSpecificationCreateDtoToBookSpecification(bookCreateDto.getBookSpecificationCreateDto());

        bookSpecification.setStartDate(LocalDate.now());
        bookSpecification.setEndDate(LocalDate.now());
        bookSpecificationRepository.save(bookSpecification);
        book.setBookSpecification(bookSpecification);
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    public Book updateBook(long id, Book _book) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        BookSpecification bookSpecification = bookSpecificationRepository
                .findById(_book.getBookSpecification().getId()).orElseThrow();

        book.setIsbn(_book.getIsbn());
        book.setName(_book.getName());
        book.setPages(_book.getPages());
        book.setPublishYear(_book.getPublishYear());

        bookSpecification.setPrice(_book.getBookSpecification().getPrice());
        bookSpecification.setStartDate(_book.getBookSpecification().getStartDate());
        bookSpecification.setEndDate(_book.getBookSpecification().getEndDate());

        bookSpecificationRepository.save(bookSpecification);
        return bookRepository.save(book);
    }

    public ResponseEntity<HttpStatus> deleteBook(Long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
