package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		System.out.println("Ashok class : LandingPage constructor");
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmails = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy( id = "userEmail")
	WebElement userEmail;
	
	@FindBy( id = "userPassword")
	WebElement passwordEle;
	
	@FindBy( id = "login")
	WebElement submit;
	
	@FindBy( css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProdctCatalog loginApplication(String email, String password) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProdctCatalog prodctCatalog = new ProdctCatalog(driver);
		return prodctCatalog;
	}

	public void goTo() {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
