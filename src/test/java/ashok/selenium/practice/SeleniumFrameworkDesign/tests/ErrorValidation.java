package ashok.selenium.practice.SeleniumFrameworkDesign.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ashok.selenium.practice.SeleniumFrameworkDesign.TestComponenets.BaseTest;
import ashok.selenium.practice.SeleniumFrameworkDesign.TestComponenets.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.CartPage;
import pageobject.CheckOutPage;
import pageobject.ConfirmationPage;
import pageobject.LandingPage;
import pageobject.ProdctCatalog;


public class ErrorValidation  extends BaseTest{

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
//		String productName = "ZARA COAT 3";

		ProdctCatalog prodctCatalog = landingPage.loginApplication("RachelPineBrook@gmail.com","IncorrectPassword");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		String productName = "ZARA COAT 3";
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//		driver.get("https://rahulshettyacademy.com/client/");
//		LandingPage landingPage = new LandingPage(driver);
//		LandingPage landingPage = launchApplication();
		ProdctCatalog prodctCatalog = landingPage.loginApplication("RachelPineBrook@gmail.com","RachelPineBrook1$");

		List<WebElement> products =  prodctCatalog.getProductList();
		prodctCatalog.addProductToCart(productName);
		CartPage cartPage = prodctCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}

}
