package setup;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Wait methods
    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Click methods
    protected void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    protected void clickUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Input methods
    protected void sendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void sendKeysSlowly(WebElement element, String text, int delayInMillis) {
        waitForElementToBeVisible(element);
        element.clear();
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(delayInMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Get methods
    protected String getText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }

    protected String getAttribute(WebElement element, String attribute) {
        waitForElementToBeVisible(element);
        return element.getAttribute(attribute);
    }

    // Dropdown methods
    protected void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    protected void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    // Scroll methods
    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Mouse actions
    protected void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    protected void dragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }

    // Verification methods
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    protected boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    // Alert methods
    protected void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    protected void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    protected String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    // Frame methods
    protected void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Window methods
    protected void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    protected String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    // List methods
    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    // Wait method with custom timeout
    protected void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Page title and URL methods
    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
