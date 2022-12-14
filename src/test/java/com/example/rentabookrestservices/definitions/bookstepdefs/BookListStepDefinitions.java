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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class) //@WebMvcTest(BookController.class)
public class BookListStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookService bookService;
    ResultActions perform;

    @Given("Kitaplar sisteme eklenmiş olmalı")
    public void kullanici_web_sitesini_acar() {
    }

    @When("Kullanıcı kitapları listelemek istediğinde")
    public void kullanici_kitaplari_listeler() throws Exception {
        perform = mockMvc.perform(get("/books"));
    }

    @Then("Dükkanda bulunan kitapları listeler")
    public void dukkanda_bulunan_kitaplari_goruntuler() throws Exception {
        perform.andExpect(status().isOk());
    }

}
