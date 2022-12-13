package com.example.rentabookrestservices.definitions;

import com.example.rentabookrestservices.controller.BookController;
import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.dto.BookPriceCreateDto;
import com.example.rentabookrestservices.mapper.BookMapper;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.BookPrice;
import com.example.rentabookrestservices.runner.CucumberIntegrationTest;
import com.example.rentabookrestservices.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = BookController.class)
public class BookCreateStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    @Autowired
    BookService bookService;

    String requestBody;
    BookPrice bookPrice;
    BookCreateDto bookCreateDto;
    Book book;

    @Given("Kullanıcı kitap bilgilerini girerr")
    public void kullanici_kitap_bilgilerini_girer() {
        bookPrice = new BookPrice(15F, LocalDateTime.now(), LocalDateTime.now());
        book = new Book("A", "A", "A", 2022, 1, bookPrice);
        bookCreateDto = BookMapper.INSTANCE.bookToBookCreateDto(book);

        BookPriceCreateDto bookPriceCreateDto = BookMapper.INSTANCE.bookPriceToBookPriceCreateDto(bookPrice);
        bookCreateDto.setBookPriceCreateDto(bookPriceCreateDto);
    }

    @When("Kullanıcı kitap ekleme butonuna tıkladığındaa")
    public void kullanici_kitap_ekleme_butonuna_tikladiginda() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        requestBody = objectMapper.writeValueAsString(bookCreateDto);
        //when(bookService.createBook(bookCreateDto)).thenReturn(book);
    }

    @Then("Kitap eklenmiş olurr")
    public void kitap_eklenmis_olur() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
}