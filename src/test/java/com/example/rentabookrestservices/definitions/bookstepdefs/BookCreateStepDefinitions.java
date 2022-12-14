package com.example.rentabookrestservices.definitions.bookstepdefs;

import com.example.rentabookrestservices.controller.BookController;
import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.dto.BookPriceCreateDto;
import com.example.rentabookrestservices.mapper.BookMapper;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.BookPrice;
import com.example.rentabookrestservices.runner.CucumberIntegrationTest;
import com.example.rentabookrestservices.service.BookService;
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
import org.springframework.test.web.servlet.ResultActions;

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
    ResultActions result;

    @Given("Kitap bilgileri girilmiştir")
    public void kitap_bilgileri_girilmistir() {
        bookPrice = new BookPrice(15F, LocalDateTime.now(), LocalDateTime.now());
        book = new Book("A", "A", "A", 2022, 1, bookPrice);
        bookCreateDto = BookMapper.INSTANCE.bookToBookCreateDto(book);

        BookPriceCreateDto bookPriceCreateDto = BookMapper.INSTANCE.bookPriceToBookPriceCreateDto(bookPrice);
        bookCreateDto.setBookPriceCreateDto(bookPriceCreateDto);
    }

    @When("Kitap ekle butonuna tıklandığında")
    public void kitap_ekle_butonuna_tikladiginda() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        requestBody = objectMapper.writeValueAsString(bookCreateDto);
        //when(bookService.createBook(bookCreateDto)).thenReturn(book);

        result = mockMvc.perform(post("/books")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Then("Kitap eklenir")
    public void kitap_eklenir() throws Exception {
        result.andExpect(status().isCreated()).andReturn();
    }

}