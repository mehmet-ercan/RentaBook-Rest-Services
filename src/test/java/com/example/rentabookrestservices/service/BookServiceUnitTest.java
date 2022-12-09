package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.dto.BookPriceCreateDto;
import com.example.rentabookrestservices.mapper.BookMapper;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.BookPrice;
import com.example.rentabookrestservices.repository.BookPriceRepository;
import com.example.rentabookrestservices.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceUnitTest {

    @Mock
    BookRepository bookRepository;
    @Mock
    BookPriceRepository bookPriceRepository;
    @Mock
    BookService bookService;

    @Test
    public void Should_Success_When_CreateBook() {
        //arrange
        bookRepository = mock(BookRepository.class);
        bookPriceRepository = mock(BookPriceRepository.class);
        bookService = mock(BookService.class);

        BookPrice bookPrice = new BookPrice(15F, LocalDateTime.now(), LocalDateTime.now());
        Book book = new Book("A", "A", "A", 2022, 1, bookPrice);

        BookCreateDto bookCreateDto = BookMapper.INSTANCE.bookToBookCreateDto(book);
        BookPriceCreateDto bookPriceCreateDto = BookMapper.INSTANCE.bookPriceToBookPriceCreateDto(bookPrice);
        bookCreateDto.setBookPriceCreateDto(bookPriceCreateDto);

        when(bookPriceRepository.save(bookPrice)).thenReturn(bookPrice);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookService.createBook(bookCreateDto)).thenReturn(book);

        //act
        BookPrice resultBookPrice = bookPriceRepository.save(bookPrice);
        Book resultBookRepository = bookRepository.save(book);
        Book resultBookService = bookService.createBook(bookCreateDto);

        //assert
        assertEquals(bookPrice,resultBookPrice);
        assertEquals(book, resultBookRepository);
        assertEquals(book, resultBookService);
    }

}
