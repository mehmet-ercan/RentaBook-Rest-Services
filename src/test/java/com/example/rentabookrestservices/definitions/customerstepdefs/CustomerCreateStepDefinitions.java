package com.example.rentabookrestservices.definitions.customerstepdefs;

import com.example.rentabookrestservices.controller.CustomerController;
import com.example.rentabookrestservices.model.Customer;
import com.example.rentabookrestservices.runner.CucumberIntegrationTest;
import com.example.rentabookrestservices.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(controllers = CustomerController.class)
public class CustomerCreateStepDefinitions extends CucumberIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    @MockBean
    CustomerService customerService;
    Customer customer;
    ResultActions resultActions;

    @Given("Musteri bilgileri olan {string}, {string} ve {string} bilgileri alinir")
    public void musteriBilgileriOlanVeBilgileriAlinir(String name, String surName, String phoneNumber) {
       customer = customerService.createCustomer(new Customer(name, surName, phoneNumber));
    }

    @When("Bilgiler ilgili alanlara girildikten sonra kaydet butonuna basilir")
    public void bilgilerIlgiliAlanlaraGirildiktenSonraKaydetButonunaBasilir() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(customer);

        resultActions = mockMvc.perform(post("/customers")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Then("Musteri eklenmis olur")
    public void musteriEklenmisOlur() throws Exception {
        resultActions.andExpect(status().isCreated()).andReturn();
    }


}
