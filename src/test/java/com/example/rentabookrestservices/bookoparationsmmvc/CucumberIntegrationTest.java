package com.example.rentabookrestservices.bookoparationsmmvc;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


//@CucumberOptions(features = "src/test/resources/features/bookoperations", plugin = {"pretty", "html:target/cucumber/bookoperations"})
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/bookoperations")
public class CucumberIntegrationTest {
}
