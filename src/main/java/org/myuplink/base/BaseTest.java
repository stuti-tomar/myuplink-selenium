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

public class BaseTest {
	public static WebDriver driver;

	@BeforeMethod
	public void browserSetup() {
		
		 // Specify the path to chromedriver
	    String chromeDriverPath;

	    // Check if we are running in Jenkins or locally
	    if (System.getenv("JENKINS_HOME") != null) {
	        // Jenkins environment
	        chromeDriverPath = "C:/ProgramData/Jenkins/tools/new/chromedriver.exe";
	    } else {
	        // Local environment (set the path of local machine)
	        chromeDriverPath = "C:\\Users\\91701\\.cache\\selenium\\chromedriver\\win64\\131.0.6778.85\\chromedriver.exe"; // update this path
	    }

	    // Set the chromedriver path dynamically
	    System.setProperty("webdriver.chrome.driver", chromeDriverPath);

	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("start-maximized");
	    options.addArguments("--remote-allow-origins=*");
	    options.setExperimentalOption("useAutomationExtension", false);
	    options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
	    options.addArguments("disable-infobars");

	    driver = new ChromeDriver(options);
	    driver.get("https://stageweb.nibejpi.com/login");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		/*ChromeOptions options = new ChromeOptions();

		options.addArguments("start-maximized");

		options.addArguments("--remote-allow-origins=*");
		options.setExperimentalOption("useAutomationExtension", false);

		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		options.addArguments("disable-infobars");

		driver = new ChromeDriver(options);
		driver.get("https://stageweb.nibejpi.com/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));*/
		
		
		

	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
	File file=new File(System.getProperty("user.dir") + "//screenshots//" + testCaseName +".png");
	FileUtils.copyFile(source, file);
	return System.getProperty("user.dir") + "//screenshots//" + testCaseName +".png";
	}

	

}
