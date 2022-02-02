package com.test.gluecode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TTDriverUtillity {
	
public static WebDriver driver;
	
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://http://localhost:9065/html/login.html");
	}
	
	@After
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	

}
