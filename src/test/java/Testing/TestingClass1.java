package Testing;

import org.openqa.selenium.Alert;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import gitHupProject.Contact_Form;
import gitHupProject.HomePage;
import gitHupProject.SearchResultPage;
import setup.DriverManager;

public class TestingClass1 extends DriverManager {
private HomePage homePage;
private SearchResultPage searchResultPage;
private Contact_Form contact_Form;
private SoftAssert softAssert;
	@BeforeMethod
	public void launchingBrowser() throws InterruptedException {
		openBrowser();
		
		driver.get("https://www.Magicbricks.com/");
		
		 homePage=new HomePage(driver);
		 searchResultPage=new SearchResultPage(driver);
		 contact_Form=new Contact_Form(driver);
		 softAssert=new SoftAssert();
	}
	
	@Test
	public void homePageVerify() {
		String currentUrlString=driver.getCurrentUrl();
		Assert.assertEquals(currentUrlString, "https://www.magicbricks.com/");
	}
	
	@Test
	public void selectCity() throws InterruptedException, TimeoutException {
		String providedCityString="Andaman & Nicobar";
		String city=homePage.selectInputCity(providedCityString);
		Assert.assertEquals(providedCityString, city);
	}
	
	@Test
	public void VerifyAgentContactCTAIsClickable() throws InterruptedException, TimeoutException {
		String providedCityString="Andaman & Nicobar";
		String city=homePage.selectInputCity(providedCityString);
		boolean result=searchResultPage.clickOnAgentContactCTA();
		Assert.assertTrue(result);
	}
	
	@Test
	public void VerifyAgentContact() throws Exception {
		String providedCityString="Andaman & Nicobar";
		String city=homePage.selectInputCity(providedCityString);
		System.out.println(city);
		Thread.sleep(5000);
		searchResultPage.clickOnAgentCTA();
	   // Assert.assertTrue(result);
		
		//softAssert.assertTrue(contact_Form.isContactFormVisible());
		contact_Form.enterBuyerName();
		contact_Form.enterBuyerEmail();
		contact_Form.enterMobileNumber();
		Thread.sleep(5000);
		contact_Form.clickOnSubmitContactForm();
		contact_Form.otpScreen();
		//System.out.println(number);
	}
	
	@AfterMethod
	public void closedBrowser() {
		//driver.quit();
	}
	
	
	
}
