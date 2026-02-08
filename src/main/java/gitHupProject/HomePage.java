package gitHupProject;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
private WebDriver driver;
private WebDriverWait wait;
private JavascriptExecutor js;

public HomePage(WebDriver driver) {
	  PageFactory.initElements(driver, this);
	  this.driver=driver;
	  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
}

	@FindBy(xpath="//input[@id='keyword']")
	private WebElement cityInput;
	
	@FindBy(xpath="//div[@class='mb-search__tag-close']")
	private WebElement removeSelectedCity1;

	@FindBy(xpath="//div[@id='serachSuggest']/div")
	private List<WebElement> listOfCity;

	@FindBy(xpath="//div[@class='mb-search__btn']")
	private WebElement searchButton;
	
	@FindBy(xpath="//div[@class='mb-search__tag-t']")
	private WebElement selectedCityElement;
	
	@FindBy(css = ".mb-search__remove")
	private WebElement removeSelectedCity;

	public String selectInputCity(String cityName) throws TimeoutException {

	    int maxRetries = 3;

	    By suggestionBy = By.xpath(
	        "//*[@id=\"serachSuggest\"]/div[2]"
	    );

	    for (int attempt = 1; attempt <= maxRetries; attempt++) {
	        try {

	            // Open input
	            wait.until(ExpectedConditions.elementToBeClickable(cityInput)).click();

	            // Remove previous city safely - only if it exists
	            List<WebElement> removeButtons = driver.findElements(By.xpath("//*[@id=\"keyword_autoSuggestSelectedDiv\"]/div/div[2]"));
	            if (!removeButtons.isEmpty()) {
	                try {
	                    wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0))).click();
	                    Thread.sleep(500); // Wait for removal animation
	                } catch (Exception e) {
	                    // Element not clickable or disappeared, continue
	                }
	            }

	            // Clear + type
	            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(cityInput));
	            input.clear();
	            input.sendKeys(cityName);

	            // Wait for the EXACT city suggestion
	            WebElement citySuggestion = wait.until(
	                ExpectedConditions.elementToBeClickable(suggestionBy)
	            );

	            // Capture data-id BEFORE click
	            String dataId = citySuggestion.getAttribute("data-id");

	            // Scroll to avoid interception
	            ((JavascriptExecutor) driver)
	                    .executeScript("arguments[0].scrollIntoView({block:'center'});", citySuggestion);
	            
	            Thread.sleep(300); // Small pause after scroll

	            citySuggestion.click();

	            // Wait for search button
	            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

	            return dataId;

	        } catch (StaleElementReferenceException | InterruptedException e) {
	            if (attempt == maxRetries) {
	                throw new TimeoutException("Failed after " + maxRetries + " attempts: " + e.getMessage(), e);
	            }
	        }
	    }
	    return null;
	}	
	
}


	
	













