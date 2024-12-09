package org.myuplink.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.Constant;

public class BaseTest {
	public static WebDriver driver;

	@BeforeMethod
	public void browserSetup() {
		String chromeDriverPath; // path to chromedriver

		// Check if running in Jenkins or locally
		if (System.getenv("JENKINS_HOME") != null) {
			// Jenkins environment
			chromeDriverPath = "C:/ProgramData/Jenkins/tools/chromedriver/chromedriver.exe";
		} else {
			// Local environment
			chromeDriverPath = "C:\\Users\\91701\\.cache\\selenium\\chromedriver\\win64\\131.0.6778.85\\chromedriver.exe";
		}

		System.setProperty("webdriver.chrome.driver", chromeDriverPath); // setting path dynamically

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--remote-allow-origins=*");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("disable-infobars");

		driver = new ChromeDriver(options);
		driver.get(Constant.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//screenshots//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//screenshots//" + testCaseName + ".png";
	}
}
