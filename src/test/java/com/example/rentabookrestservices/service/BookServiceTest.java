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
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @Mock
    BookPriceRepository bookPriceRepository;
    @Mock
    BookService bookService;


    @Test
    public void Should_Success_When_CreateBook_WithRepository() {
        //arrange
        bookRepository = mock(BookRepository.class);
        bookPriceRepository = mock(BookPriceRepository.class);

        bookService = new BookService(bookRepository, bookPriceRepository);
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
        Book resultBook = bookRepository.save(book);

        //TODO repository değil de servis içerisindeki creteBook fonksiyonu dönüşünden gelen book nesnesini assert et!
        //Book resultBook = bookService.createBook(bookCreateDto);

        //assert
        assertEquals(book, resultBook);
    }

    @Test
    public void Should_Success_When_CreateBook_WithService() {
        //arrange
        bookRepository = mock(BookRepository.class);
        bookPriceRepository = mock(BookPriceRepository.class);

        bookService = new BookService(bookRepository, bookPriceRepository);
        BookPrice bookPrice = new BookPrice(15F, LocalDateTime.now(), LocalDateTime.now());
        Book book = new Book("A", "A", "A", 2022, 1, bookPrice);

        BookCreateDto bookCreateDto = BookMapper.INSTANCE.bookToBookCreateDto(book);
        BookPriceCreateDto bookPriceCreateDto = BookMapper.INSTANCE.bookPriceToBookPriceCreateDto(bookPrice);
        bookCreateDto.setBookPriceCreateDto(bookPriceCreateDto);

        when(bookService.createBook(bookCreateDto)).thenReturn(book);

        //act
        Book resultBook = bookService.createBook(bookCreateDto);

        //assert
        assertEquals(book, resultBook);
    }
}
