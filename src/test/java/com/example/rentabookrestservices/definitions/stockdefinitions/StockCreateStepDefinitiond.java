package com.example.rentabookrestservices.definitions.stockdefinitions;

import com.example.rentabookrestservices.controller.StockController;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.Stock;
import com.example.rentabookrestservices.repository.BookPriceRepository;
import com.example.rentabookrestservices.service.BookService;
import com.example.rentabookrestservices.service.StockService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StockController.class)
public class StockCreateStepDefinitiond {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    @MockBean
    StockService stockService;


    @Autowired
    @MockBean
    BookService bookService;
    Book book;
    Stock stock;
    ObjectMapper objectMapper;
    @Autowired
    BookPriceRepository bookPriceRepository;

    ResultActions resultActions;

    @Given("Sistemde stok eklenecek olan kitap kayıtlı olmalıdır")
    public void sistemdeStokEklenecekOlanKitapKayitliOlmalidir() {
        book = bookService.getBookByIsbnNumber("123-45").get(0);
    }

    @When("Kayıtlı kitaba ait stok girildiğinde")
    public void kayitliKitabaAitStokGirildiginde() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        stock = stockService.createStock(new Stock(12, "A1-23", book));

        String requestBody = objectMapper.writeValueAsString(stock);
        resultActions = mockMvc.perform(post("/stocks")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print());

    }

    @Then("Stok bilgisi kaydedilmiş olur")
    public void stokBilgisiKaydedilmisOlur() throws Exception {
        resultActions.andExpect(status().isCreated());
    }
}
