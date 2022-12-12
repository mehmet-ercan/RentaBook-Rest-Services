package com.example.rentabookrestservices.bookoparationsmmvc;

import com.example.rentabookrestservices.bookoperations.CucumberIntegrationTest;
import com.example.rentabookrestservices.controller.BookController;
import com.example.rentabookrestservices.service.BookService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
public class BookOperationsMMvcStepDefinitions extends CucumberIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Given("Kullanıcı web sitesini açarr")
    public void kullanıcıWebSitesiniAcarr() {
    }

    @When("Kullanıcı kitapları listelemek istediğindee")
    public void kullanıcıKitaplarıListelemekIstedigindee() {
        //when(bookService.getAllBooks()).thenReturn("");
    }

    @Then("Dükkanda bulunan kitapları görüntülerr")
    public void dükkandaBulunanKitaplarıGoruntuler() throws Exception {

        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

}
