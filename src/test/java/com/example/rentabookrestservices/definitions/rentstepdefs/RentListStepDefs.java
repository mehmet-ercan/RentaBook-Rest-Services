package com.example.rentabookrestservices.definitions.rentstepdefs;

import com.example.rentabookrestservices.controller.RentController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RentController.class)
public class RentListStepDefs {
    @Autowired
    MockMvc mockMvc;
    String operationNumber;
    ResultActions resultIsValidRent;

    @Given("Kiralama islemi daha önceden yapilmis olup gecerli bir {string} vardir")
    public void kiralamaIslemiDahaÖncedenYapilmisOlupGecerliBirVardir(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    @When("Fis numarasi girildiginde")
    public void fisNumarasiGirildiginde() throws Exception {
        resultIsValidRent = mockMvc.perform(get("/rents/" + operationNumber));
    }

    @Then("Ilgili kiralama bilgilerine erisilir")
    public void ilgiliKiralamaBilgilerineErisilir() throws Exception {
        resultIsValidRent.andExpect(status().isOk());
    }

}
