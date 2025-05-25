package ashok.selenium.practice.SeleniumFrameworkDesign.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ashok.selenium.practice.SeleniumFrameworkDesign.TestComponenets.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.CartPage;
import pageobject.CheckOutPage;
import pageobject.ConfirmationPage;
import pageobject.LandingPage;
import pageobject.OrderPage;
import pageobject.ProdctCatalog;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2  extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//		driver.get("https://rahulshettyacademy.com/client/");
//		LandingPage landingPage = new LandingPage(driver);
//		LandingPage landingPage = launchApplication();
		ProdctCatalog prodctCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));

		List<WebElement> products =  prodctCatalog.getProductList();
		prodctCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = prodctCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage =  cartPage.goToCheckout();
		checkOutPage.seelctCountry("india");
		ConfirmationPage confirmationPage =  checkOutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();
	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		ProdctCatalog prodctCatalog = landingPage.loginApplication("RachelPineBrook@gmail.com","RachelPineBrook1$");	
		OrderPage orderPage = prodctCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	

	
	@DataProvider
	public Object[][] getData() throws IOException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "RachelPineBrook@gmail.com");
//		map.put("password", "RachelPineBrook1$");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "RachelPineBrook1@gmail.com");
//		map1.put("password", "RachelPineBrook1$");
//		map1.put("product", "ADIDAS ORIGINAL");
	
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
				
	}
}
