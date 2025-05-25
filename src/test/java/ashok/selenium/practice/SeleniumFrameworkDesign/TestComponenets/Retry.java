package ashok.selenium.practice.SeleniumFrameworkDesign.TestComponenets;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count =0;
	int maxTry = 1;
	@Override
	public boolean retry(ITestResult result) {
		System.out.println("Ashok class : "+ this.getClass().getSimpleName() + " Method Name : " + new Object(){}.getClass().getEnclosingMethod().getName());
		// TODO Auto-generated method stub
		if(count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
