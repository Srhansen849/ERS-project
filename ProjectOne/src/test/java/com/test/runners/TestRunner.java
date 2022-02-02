package com.test.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"feature/EmployeeLogin.feature", "feature/ManagerLogin.feature"},
		glue = {"com.test.gluecode"}
	)
public class TestRunner {
	
	

}
