package com.example.rentabookrestservices.definitions.stockdefinitions;

import com.example.rentabookrestservices.controller.StockController;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.service.BookService;
import com.example.rentabookrestservices.service.StockService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StockController.class)
public class StockFindByBookIdStepDefinitions {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    @Autowired
    StockService stockService;

    @MockBean
    @Autowired
    BookService bookService;

    long bookId;
    Book book;
    ResultActions resultActions;

    @Given("İlgili kitabın id <{long}> numarası bilinmelidir")
    public void i̇lgiliKitabınIdNumarasıBilinmelidir(long bookId) {
        this.bookId = bookId;
        book = bookService.getBookById(bookId);
    }

    @When("Kullanıcı kitabın stock bilgilerini sorguladığında")
    public void kullaniciKitabinStockBilgileriniSorguladiginda() throws Exception {
        resultActions = mockMvc.perform(get("/stocks/q?bookId=" + bookId));
    }

    @Then("ilgili kitabın stok bilgilerine ulaşılmış olur")
    public void ilgiliKitabinStokBilgilerineUlasilmisOlur() throws Exception {
        resultActions.andExpect(status().isOk());
    }

    @Given("Kayıtlı olmayan bir kitabın id <{long}> numarası olmalıdır")
    public void kayitliOlmayanBirKitabinIdNumarasiOlmalidir(long bookId) {
        this.bookId = bookId;
        book = bookService.getBookById(bookId);
    }

    @Then("Kitabın kayıtlı olmadığından dolayı kullanıcı hata alır")
    public void kitabinKayitliOlmadigindanDolayiKullaniciHataAlir() throws Exception {
        resultActions.andExpect(status().isOk());
    }
}
