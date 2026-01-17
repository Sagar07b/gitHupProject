package Testing;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import setup.DriverManager;

public class TestingClass1 extends DriverManager {

	@BeforeMethod
	public void launchingBrowser() throws InterruptedException {
		openBrowser();
		
		driver.get("https://www.Magicbricks.com/");
		// driver.get("https://testautomationpractice.blogspot.com/");
	}
	
	@Test
	public void homePageVerify() {
		String currentUrlString=driver.getCurrentUrl();
		Assert.assertEquals(currentUrlString, "https://www.magicbricks.com/");
	}
	
	@AfterMethod
	public void closedBrowser() {
		driver.quit();
	}
	
	
	
}
