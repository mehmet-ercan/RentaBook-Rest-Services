package com.example.rentabookrestservices.definitions.stockdefinitions;

import com.example.rentabookrestservices.controller.StockController;
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
public class StockListStepDefs {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    @MockBean
    StockService stockService;

    ResultActions resultActions;

    @Given("Kitap stok bilgileri sisteme eklenmiş olmalı")
    public void kitapStokBilgileriSistemeEklenmisOlmali() {
    }

    @When("Stok bilgileri listelenmek istendiğinde")
    public void stokBilgileriListelenmekIstendiginde() throws Exception {
        resultActions = mockMvc.perform(get("/stocks"));
    }

    @Then("Sistemde kayıtlı olan stok bilgileri gelmelidir")
    public void sistemdeKayitliOlanStokBilgileriGelmelidir() throws Exception {
        resultActions.andExpect(status().isOk());
    }
}
