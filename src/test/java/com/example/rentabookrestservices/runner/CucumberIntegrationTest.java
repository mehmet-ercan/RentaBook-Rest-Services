package com.example.rentabookrestservices.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//@CucumberOptions(features =  "src/test/resources/features/bookoperations", )
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/cucumber/test.html"},
        glue = {"com.example.rentabookrestservices.definitions", "com.example.rentabookrestservices.configurations"})
public class CucumberIntegrationTest {
}
