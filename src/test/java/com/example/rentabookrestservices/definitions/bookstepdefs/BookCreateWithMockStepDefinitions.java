package com.example.rentabookrestservices.definitions.bookstepdefs;

import com.example.rentabookrestservices.controller.BookController;
import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.dto.BookPriceCreateDto;
import com.example.rentabookrestservices.mapper.BookMapper;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.BookPrice;
import com.example.rentabookrestservices.runner.CucumberIntegrationTest;
import com.example.rentabookrestservices.service.BookService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = BookController.class)
public class BookCreateWithMockStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    BookService bookService;
    Book book;
    BookCreateDto bookCreateDto;

    @Given("Kullanıcı kitap bilgilerini girer")
    public void kullaniciKitapBilgileriniGirer() {
        bookService = mock(BookService.class);
    }

    @When("Kullanıcı kitap ekleme butonuna tıkladığında")
    public void kullaniciKitapEklemeButonunaTikladiginde() {
        BookPrice bookPrice = new BookPrice(15F, LocalDateTime.now(), LocalDateTime.now());
        book = new Book("A", "A", "A", 2022, 1, bookPrice);

        bookCreateDto = BookMapper.INSTANCE.bookToBookCreateDto(book);
        BookPriceCreateDto bookPriceCreateDto = BookMapper.INSTANCE.bookPriceToBookPriceCreateDto(bookPrice);
        bookCreateDto.setBookPriceCreateDto(bookPriceCreateDto);

        when(bookService.createBook(bookCreateDto)).thenReturn(book);
    }

    @Then("Kitap eklenmiş olur")
    public void kitapEklenmisOlur() {
        Book resultBookService = bookService.createBook(bookCreateDto);
        assertEquals(book, resultBookService);
    }
}
