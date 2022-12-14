package com.example.rentabookrestservices.definitions.customerstepdefs;

import com.example.rentabookrestservices.controller.CustomerController;
import com.example.rentabookrestservices.service.CustomerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerValidStepDefinitions {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    @MockBean
    CustomerService customerService;
    int customerNumber;
    ResultActions resultActions;

    @Given("Musteri numarasi bilinmelidir <{int}>")
    public void musteriNumarasiBilinmelidir(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    @When("Musteri numarasi ilgili alana girilir")
    public void musteriNumarasiIlgiliAlanaGirilir() throws Exception {
        resultActions = mockMvc.perform(get("/customers/" + customerNumber)).andDo(print());
    }

    @Then("Islem yapilacak olan musteri sistem var oldugu anlasilir")
    public void islemYapilacakOlanMusteriSistemVarOlduguAnlasilir() throws Exception {
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

}
