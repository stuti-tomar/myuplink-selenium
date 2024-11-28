package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor to initialize driver and wait
	public WaitUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Default wait time
	}

	// Wait for visibility of an element using WebElement
	public WebElement waitForVisibilityOfElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	// Wait for all elements in a list to be visible
	public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> elements) {
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	// Wait for element to be clickable
	public WebElement waitForClickability(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
