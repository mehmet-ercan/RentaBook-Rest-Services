package com.example.rentabookrestservices.definitions.rentstepdefs;

import com.example.rentabookrestservices.controller.RentController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = RentController.class)
public class RentCancelStepDefinitions {
    @Autowired
    MockMvc mockMvc;
    String operationNumber;
    ResultActions resultIsValidRent, resultCancelRent;

    @Given("Iptal islemi icin gecerli bir {string} fis numarasi vardir")
    public void iptalIslemiIcinGecerliBirFisNumarasiVardir(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    @When("Fis numarasi girilip iptal butonuna basildiginda")
    public void fisNumarasiGirilipIptalButonunaBasildiginda() throws Exception {
        resultIsValidRent = mockMvc.perform(get("/rents/" + operationNumber));
        resultCancelRent = mockMvc.perform(delete("/rents/" + operationNumber));
    }

    @Then("Kitap kiralama islemi iptal edilmelidir")
    public void kitapKiralamaIslemiIptalEdilmelidir() throws Exception {
        resultIsValidRent.andExpect(status().isOk());
        resultCancelRent.andExpect(status().isOk()).andReturn();
    }

}
