package utils;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSExecutorUtils {

	private WebDriver driver;

	// Constructor to initialize driver
	public JSExecutorUtils(WebDriver driver) {
		this.driver = driver;
	}

	// Scroll to an element
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Click an element using JavaScript
	public void clickUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// Scroll to the bottom of the page
	public void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public void clickWithRetry(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    for (int attempt = 0; attempt < 3; attempt++) {
	        try {
	            // Ensure the element is clickable
	            wait.until(ExpectedConditions.elementToBeClickable(element));
	            
	            // Click the element
	            element.click();
	            return; // Exit if successful
	        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
	            System.out.println("Retrying click on element. Attempt: " + (attempt + 1));
	        }
	    }
	    throw new RuntimeException("Failed to click element after 3 retries.");
	}
	}

