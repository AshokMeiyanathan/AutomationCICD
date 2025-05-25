package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		System.out.println("Ashok class : ConfirmationPage constructor");
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy( css = ".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage() {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		return confirmationMessage.getText();
	}

}
