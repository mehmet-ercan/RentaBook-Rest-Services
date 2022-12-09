package com.example.rentabookrestservices.examplecalc;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.junit.Assert;


public class CalculatorOperaionsStepDefinitions {
    public class Calc {
        int add(int n1, int n2) {
            return n1 + n2;
        }
    }

    private Calc c;
    private int total;

    @Given("Hesap makinesi vardır")
    public void hesapMakinesiVardır() {
        c = new Calc();
    }

    @When("İki sayı topladığımda {int} ve {int}")
    public void i̇kiSayıTopladığımdaVe(int arg0, int arg1) {
        total = c.add(arg1, arg0);
    }

    @Then("Sonuç olarak geriye {int} döner")
    public void sonuçOlarakGeriyeDöner(int result) {
        Assert.assertThat(total, Matchers.equalTo(result));
    }
}
