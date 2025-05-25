package ashok.selenium.practice.SeleniumFrameworkDesign.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver intaliaizeDriver() throws IOException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		//Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +  "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :   prop.getProperty("browser");;
		//String browserName = prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			ChromeOptions chromeOpetion = new ChromeOptions();
			
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")){
			chromeOpetion.addArguments("headless");	
		}
		
		
		String productName = "ZARA COAT 3";
		driver = new ChromeDriver(chromeOpetion);
		driver.manage().window().setSize(new Dimension(1440, 900));//full screen
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\ashok\\Desktop\\UdemySelenium\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			//Edge
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver = intaliaizeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.close();}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException  {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//string to Hashmap jackson databind
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
		
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}

}
