package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.exception.BookNotFoundException;
import com.example.rentabookrestservices.mapper.BookMapper;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.BookPrice;
import com.example.rentabookrestservices.repository.BookPriceRepository;
import com.example.rentabookrestservices.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookPriceRepository bookPriceRepository;

    public BookService(BookRepository bookRepository, BookPriceRepository bookPriceRepository) {
        this.bookRepository = bookRepository;
        this.bookPriceRepository = bookPriceRepository;
    }

    public List<Book> getAllBooks() {
        bookRepository.findAll();
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return book;
    }


    public Book createBook(BookCreateDto bookCreateDto) {
        Book book = BookMapper.INSTANCE.bookCreateDtoToBook(bookCreateDto);
        BookPrice bookPrice = BookMapper.INSTANCE.bookPriceCreateDtoToBookPrice(bookCreateDto.getBookPriceCreateDto());

        bookPrice.setStartDate(LocalDate.now());
        bookPrice.setEndDate(LocalDate.parse("9999-12-31"));
        bookPriceRepository.save(bookPrice);
        book.setBookPrice(bookPrice);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book _book) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        BookPrice bookPrice = bookPriceRepository
                .findById(_book.getBookPrice().getId()).orElseThrow();

        book.setIsbn(_book.getIsbn());
        book.setName(_book.getName());
        book.setPages(_book.getPages());
        book.setPublishYear(_book.getPublishYear());

        bookPrice.setPrice(_book.getBookPrice().getPrice());
        bookPrice.setStartDate(_book.getBookPrice().getStartDate());
        bookPrice.setEndDate(_book.getBookPrice().getEndDate());

        bookPriceRepository.save(bookPrice);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBookByIsbnNumber(String isbnNumber) {
        return bookRepository.findBookByIsbn(isbnNumber);
    }
}
