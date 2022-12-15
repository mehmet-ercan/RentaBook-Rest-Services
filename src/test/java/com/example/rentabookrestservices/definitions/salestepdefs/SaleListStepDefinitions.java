package com.example.rentabookrestservices.definitions.salestepdefs;

import com.example.rentabookrestservices.controller.SaleController;
import com.example.rentabookrestservices.runner.CucumberIntegrationTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = SaleController.class)
public class SaleListStepDefinitions extends CucumberIntegrationTest {

    @Given("Satinalma islemi daha onceden yapilmis olup gecerli bir fis numarasi {string} vardir")
    public void satinalmaIslemiDahaOncedenYapilmisOlupGecerliBirFisNumarasiVardir(String arg0) {
    }

    @When("Fis numarasi girildigince")
    public void fisNumarasiGirildigince() {
    }

    @Then("Ilgili satinalma bilgilerine erisilir")
    public void ilgiliSatinalmaBilgilerineErisilir() {
    }

}
