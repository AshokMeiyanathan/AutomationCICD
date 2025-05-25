package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	@FindBy( css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy( css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		System.out.println("Ashok class : OrderPage constructor");
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyOrderDisplay(String productName) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
