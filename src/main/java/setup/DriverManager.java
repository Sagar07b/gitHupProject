package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
	protected WebDriver driver;
	public void openBrowser() throws InterruptedException {

	 ChromeOptions options = new ChromeOptions();

      driver = new ChromeDriver(options);
     Thread.sleep(3000);

     // Maximize window
     driver.manage().window().maximize();
	}
	
	public  void quitDriver() {
          driver.quit();
        }
    
}
