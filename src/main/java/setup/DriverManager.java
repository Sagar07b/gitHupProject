package setup;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	protected WebDriver driver;
	public void openBrowser() throws InterruptedException {

		 WebDriverManager.chromedriver().setup();

	        ChromeOptions options = new ChromeOptions();
	        //options.setPageLoadStrategy(PageLoadStrategy.EAGER); // ✅ Don't wait for full load
	        options.addArguments("--remote-allow-origins=*");
	        options.addArguments("--start-maximized");
	        options.addArguments("--disable-popup-blocking");
	      //  options.addArguments("--headless");
	       // options.addArguments("--disable-notifications");

	        Map<String, Object> prefs = new HashMap<>();
	        prefs.put("profile.default_content_setting_values.notifications", 2);
	        options.setExperimentalOption("prefs", prefs);

	        driver = new ChromeDriver(options);
	        driver.manage().deleteAllCookies();
	}
	
	public  void quitDriver() {
          driver.quit();
        }
    
}
