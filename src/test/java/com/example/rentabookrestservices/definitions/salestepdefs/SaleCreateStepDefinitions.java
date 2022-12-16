package com.example.rentabookrestservices.definitions.salestepdefs;

import com.example.rentabookrestservices.controller.SaleController;
import com.example.rentabookrestservices.dto.SaleCreateDto;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.OrderBookItems;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SaleController.class)
public class SaleCreateStepDefinitions {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    @Autowired
    BookService bookService;

    Book book;
    SaleCreateDto saleCreateDto;
    ObjectMapper objectMapper;
    ResultActions result;

    @Given("Satin alinacak kitap ve musteri bilgileri girilmelidir")
    public void satinAlinacakKitapVeMusteriBilgileriGirilmelidir() {
        book = bookService.getBookByIsbnNumber("123-45").get(0);
        OrderBookItems orderBookItems = new OrderBookItems(book, 1);
        List<OrderBookItems> orderBookItemsList = List.of(orderBookItems);
        saleCreateDto = new SaleCreateDto(orderBookItemsList, 3, 120);
    }

    @When("Kitap satış işlemi gerçekleştiğinde")
    public void kitapSatisIslemiGerceklestiginde() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String requestBody = objectMapper.writeValueAsString(saleCreateDto);
        result = mockMvc.perform(post("/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Then("Kitap ilgili musteriye satilmis olmalidir")
    public void kitapIlgiliMusteriyeSatilmisOlmalidir() throws Exception {
        result.andExpect(status().isCreated());
    }
}
