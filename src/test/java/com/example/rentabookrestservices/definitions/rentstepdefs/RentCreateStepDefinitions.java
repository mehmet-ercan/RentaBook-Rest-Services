package com.example.rentabookrestservices.definitions.rentstepdefs;

import com.example.rentabookrestservices.dto.RentCreateDto;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.OrderBookItems;
import com.example.rentabookrestservices.runner.CucumberIntegrationTest;
import com.example.rentabookrestservices.service.BookService;
import com.example.rentabookrestservices.service.RentService;
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

@WebMvcTest(controllers = RentService.class)
public class RentCreateStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    @Autowired
    RentService rentService;

    @MockBean
    @Autowired
    BookService bookService;

    RentCreateDto rentCreateDto;
    ObjectMapper objectMapper;
    ResultActions result;

    @Given("Kiralanacak kitap ve musteri bilgileri girilmelidir")
    public void kiralanacakKitapVeMusteriBilgileriGirilmelidir() {
        Book _book = bookService.getBookByIsbnNumber("123-45").get(0);

        OrderBookItems orderBookItems = new OrderBookItems(_book, 1);
        List<OrderBookItems> orderBookItemsList = List.of(orderBookItems);
        rentCreateDto = new RentCreateDto(orderBookItemsList, 3, 300);
    }

    @When("Kitap kiralama işlemi gerçekleştiğinde")
    public void kitapKiralamaIslemiGerceklestiginde() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String requestBody = objectMapper.writeValueAsString(rentCreateDto);
        result = mockMvc.perform(post("/rents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Then("Kitap ilgili musteriye kiralanmalidir")
    public void kitapIlgiliMusteriyeKiralanmalidir() throws Exception {
        result.andExpect(status().isCreated());
    }


}
