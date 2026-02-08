package gitHupProject;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.codec.AsciiHeadersEncoder.NewlineType;

public class SearchResultPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor jsExecutor;
	
	public SearchResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		 wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		 jsExecutor=(JavascriptExecutor)driver;
	}

//	@FindBy(xpath="(//span[text()='Contact Agent'])[1]")
//	private WebElement agentContactCTAElement;
	
	@FindBy(xpath="//span[normalize-space()='Contact Agent']")
    private WebElement agentContactCTAElement;
	
	public boolean clickOnAgentContactCTA() {
		try {
	        WebElement cta = wait.until(
	            ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//span[text()='Contact Agent']")
	            )
	        );
	        Thread.sleep(6000);

	        jsExecutor.executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", cta
	        );

	        Thread.sleep(6000); // UI settle time

	        jsExecutor.executeScript("arguments[0].click();", cta);

	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	
}
	public void clickOnAgentCTA() throws InterruptedException {
	By contactAgentCTA = By.xpath(
		    "//span[normalize-space()='Contact Agent']"
		);

		WebElement cta = wait.until(ExpectedConditions.presenceOfElementLocated(contactAgentCTA));

		((JavascriptExecutor) driver)
		        .executeScript("arguments[0].scrollIntoView({block:'center'});", cta);
		 Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(cta)).click();
}
}
	
	
	
	
	
	
