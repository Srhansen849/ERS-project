package com.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TobyTechLogin {
	
	@FindBy(xpath = "//input[@name='userName']")
	private WebElement usernane;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(xpath = "//input[@type='submit'][@name='submit']")
	private WebElement submit;
	
	
	public TobyTechLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void loginToTobyTech(String username, String password) {
		this.usernane.clear();
		this.password.clear();
		this.usernane.sendKeys(username);
		this.password.sendKeys(password);
		this.submit.click();
	}



}
