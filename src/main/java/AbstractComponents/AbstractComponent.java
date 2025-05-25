package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobject.CartPage;
import pageobject.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		System.out.println("Ashok class : AbstractComponent constructor");
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy( css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy( css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy){
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public CartPage goToCartPage() {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToDisappear(WebElement findBy) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
		
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}

}
