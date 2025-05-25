package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProdctCatalog extends AbstractComponent{
	
	WebDriver driver;
	
	public ProdctCatalog(WebDriver driver) {
		//initialization
		super(driver);
		System.out.println("Ashok class : ProdctCatalog constructor");
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy( css = ".mb-3")
	List<WebElement> products;
	
	@FindBy( css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		WebElement prod = getProductList().stream().filter( product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName){
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	
}
