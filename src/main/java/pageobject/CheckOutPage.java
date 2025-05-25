package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
		WebDriver driver;
	
		public CheckOutPage(WebDriver driver) {
			super(driver);
			System.out.println("Ashok class : CheckOutPage constructor");
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy( css = ".action__submit")
		private WebElement submit;
		
		@FindBy( css = "[placeholder='Select Country']")
		private WebElement country;
		
		@FindBy( xpath = "//button[contains(@class,'ta-item')][2]")
		private WebElement selectCountry;
		//above three are page factory elements
		
		By results = By.cssSelector(".ta-results");
		
		public void seelctCountry(String countryName) {
			System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
			Actions a = new Actions(driver);
			a.sendKeys(country,"india").build().perform();
			waitForElementToAppear(results);
			selectCountry.click();
		}
	
		public ConfirmationPage submitOrder() {
			System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
			submit.click();
			return new ConfirmationPage(driver);
		}

}
