package com.example.rentabookrestservices.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//@CucumberOptions(features =  "src/test/resources/features/bookoperations", plugin = {"pretty", "html:target/cucumber/bookoperations"})
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/bookoperations"
        , glue = "src/test/java/com/example/rentabookrestservices/definitions")
public class CucumberIntegrationTest {
}
