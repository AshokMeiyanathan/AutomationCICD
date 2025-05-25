package ashok.selenium.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ashok.selenium.practice.SeleniumFrameworkDesign.TestComponenets.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.CartPage;
import pageobject.CheckOutPage;
import pageobject.ConfirmationPage;
import pageobject.LandingPage;
import pageobject.ProdctCatalog;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProdctCatalog prodctCatalog;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with usrname (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) throws IOException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		prodctCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		List<WebElement> products =  prodctCatalog.getProductList();
		prodctCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		CartPage cartPage = prodctCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage =  cartPage.goToCheckout();
		checkOutPage.seelctCountry("india");
		confirmationPage =  checkOutPage.submitOrder();
	}
	
	@Then("{string} message is diplayed on ConfirmationPage")
	public void message_displayed_on_ConfirmationPage(String string) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
String confirmationMessage = confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.close();
		
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable{
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
