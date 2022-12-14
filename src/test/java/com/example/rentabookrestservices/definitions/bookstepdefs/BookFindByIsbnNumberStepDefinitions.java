package com.example.rentabookrestservices.definitions.bookstepdefs;

import com.example.rentabookrestservices.controller.BookController;
import com.example.rentabookrestservices.runner.CucumberIntegrationTest;
import com.example.rentabookrestservices.service.BookService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
public class BookFindByIsbnNumberStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    @MockBean
    BookService bookService;
    String isbn;
    ResultActions resultActions;

    @Given("Kitabın isbn numarası biliniyor")
    public void kitabinIsbnNumarasiIlgiliMetinKutusunaYazilir() {
        isbn = "123-45";
    }

    @When("Kitap isbn numarası ile beraber aratıldığında")
    public void kitapIsbnNumarasiIleBeraberAratilir() throws Exception {
        resultActions = mockMvc.perform(get("/books/q?isbn=" + isbn));
    }

    @Then("Aranan kitap dükkanda bulunur")
    public void arananKitapDükkandaBulunur() throws Exception {
        resultActions.andExpect(status().isOk()).andDo(print()).andReturn();
    }

}
