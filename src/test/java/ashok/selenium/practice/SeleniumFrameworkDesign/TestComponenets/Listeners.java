package ashok.selenium.practice.SeleniumFrameworkDesign.TestComponenets;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();//Thread Safe
	

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extenttest.set(test);//unique thread id (ErrorValidation) -. Test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		extenttest.get().log(Status.PASS, "Test Passed");//here we are not using test.pass() because if the test is not fails by default it makes the test as pass (Entering in onTestSuccess(ITestResult result))
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		extenttest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extenttest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		extenttest.get().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		extent.flush();
	}

}
