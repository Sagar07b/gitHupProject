package gitHupProject;

import java.security.PrivateKey;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Contact_Form {
  private WebDriver driver;
  private WebDriverWait wait;
  private JavascriptExecutor js;
  public Contact_Form(WebDriver driver) {
	  PageFactory.initElements(driver, this);
	  this.driver=driver;
	  wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	  
	  js = (JavascriptExecutor) driver;
  }
  
  @FindBy(xpath="//div[@id='contact-default']")
  private WebElement contactForm;

  @FindBy(xpath="//input[@id='userName']")
  private WebElement userNameElement;

  @FindBy(xpath="//input[@id='userEmail']")  // Changed from label to input
  private WebElement userEmailElement;

  @FindBy(xpath="//div[contains(@class,'contact-form__fieldset') and contains(@class,'has-success')][1]")
  private WebElement nameSuccessState;

  @FindBy(xpath="//div[contains(@class,'contact-form__fieldset') and contains(@class,'has-success')][2]")
  private WebElement emailSuccessState;

  @FindBy(xpath="//button[@type='submit']")
  private WebElement getContactDetailsElement;
  
  @FindBy(xpath="//label[text()='Mobile Number']")
  private WebElement mobileNumberElement;
  
  @FindBy(xpath="//div[contains(@class,'contact-form__fieldset') and contains(@class,'has-success')][3]")
  private WebElement mobileNumberSuccessState;
  
  @FindBy(xpath="//div[@class='contact-form__error']")
  private WebElement contactFormError;
  
  @FindBy(xpath="//button[@type='submit']")
  private WebElement submitButtonElement;
  
  @FindBy(xpath="//div[@id='contact-default']")
  private WebElement otpScreen;
  
  @FindBy(xpath="//input[@name='otp']")
  private WebElement enterOtpElement;
  
  
  public boolean isContactFormVisible() {
      try {
          wait.until(ExpectedConditions.visibilityOf(contactForm));
          return contactForm.isDisplayed();
      } catch (Exception e) {
          return false;
      }
  }

  public boolean enterBuyerName() {
      try {
          wait.until(ExpectedConditions.visibilityOf(userNameElement));
          userNameElement.clear();
          userNameElement.sendKeys("Mayur");

          // Click outside to trigger validation (click email label or another field)
          wait.until(ExpectedConditions.elementToBeClickable(userEmailElement)).click();

          // Wait for success state to appear
          wait.until(ExpectedConditions.visibilityOf(nameSuccessState));
          return nameSuccessState.isDisplayed();
      } catch (Exception e) {
          System.out.println("Error in enterBuyerName: " + e.getMessage());
          return false;
      }
  }

  public boolean enterBuyerEmail() {
      try {
          wait.until(ExpectedConditions.visibilityOf(userEmailElement));
          userEmailElement.clear();  // Added clear before sendKeys
          userEmailElement.sendKeys("magicbricks516@gmail.com");
          
          // Click submit button
          wait.until(ExpectedConditions.elementToBeClickable(getContactDetailsElement)).click();
          
          // Wait for success state to appear
          wait.until(ExpectedConditions.visibilityOf(emailSuccessState));
          return emailSuccessState.isDisplayed();
      } catch (Exception e) {
          System.out.println("Error in enterBuyerEmail: " + e.getMessage());
          return false;
      }
  }
  
  public boolean enterMobileNumber() {
		    String mobileNumber = "9650986278";
		    int maxAttempts = 2;

		    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
		        try {
		            WebElement mobileField = wait.until(
		                    ExpectedConditions.elementToBeClickable(mobileNumberElement)
		            );

		            mobileField.click();
		            mobileField.clear();
		            mobileField.sendKeys(mobileNumber);

		            // If error appears, retry
		            if (isElementDisplayed(contactFormError)) {
		                System.out.println("Mobile number error shown. Retrying attempt: " + attempt);
		                continue;
		            }

		            // Success state
		            wait.until(ExpectedConditions.visibilityOf(mobileNumberSuccessState));
		            return mobileNumberSuccessState.isDisplayed();

		        } catch (Exception e) {
		            System.out.println("Attempt " + attempt + " failed: " + e.getMessage());
		        }
		    }
		    return false;
		}

	private boolean isElementDisplayed(WebElement contactFormError2) {
	// TODO Auto-generated method stub
	return false;
}

	
  
  public void clickOnSubmitContactForm() {
	  wait.until(ExpectedConditions.visibilityOfAllElements(submitButtonElement));
	  submitButtonElement.click();
  }

  public void otpScreen() {
//	  wait.until(ExpectedConditions.visibilityOf(otpScreen));
//	  WebElement container = driver.findElement(By.xpath(
//			    "//*[@id=\"contact-default\"]/div/div[2]/div"
//			));
//
//			String mobile = container.getText().replaceAll("\\D", "");
//			System.out.println(mobile);
		
			
			wait.until(ExpectedConditions.visibilityOf(enterOtpElement));
			enterOtpElement.sendKeys("1234");
			
			//return mobile;
  }
  
  
  
  
  
  
  
  
}
