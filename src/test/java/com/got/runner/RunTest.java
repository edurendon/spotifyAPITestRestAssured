package com.got.runner;

import cucumber.api.CucumberOptions; 
import cucumber.api.junit.Cucumber; 
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)

@CucumberOptions(format = { "pretty", "html:target/cucumber","json:target/cucumber/allTest.json"},
glue = {
		"com.got.steps.common",
},
features = "src/main/resources/cucumber"
)

public class RunTest {

}


