package com.example.rentabookrestservices.bookoperations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class BookOperationsStepDefinitions extends CucumberIntegrationTest {
    private static final String BASE_URL = "http://localhost:3002";
    private static final String BOOKS_URI = "/api/v1/books";
    private static Response response;

    @Given("API servisi başlatıldı")
    public void apiServisiBaşlatıldı() {
    }

    @When("Müşteri istek yaptığında")
    public void müşteriIstekYaptığında() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get(BOOKS_URI);
    }

    @Then("Geriye {int} http durum kodu döner")
    public void geriyeHttpDurumKoduDöner(int arg0) {
        Assert.assertEquals(arg0, response.getStatusCode());
    }

}
