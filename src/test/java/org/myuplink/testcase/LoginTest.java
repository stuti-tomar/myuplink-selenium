package org.myuplink.testcase;

import java.util.Set;

import org.myuplink.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Constant;
import pageEvents.LoginPage;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {
	
	private LoginPage loginPage;
	
	
	@BeforeMethod
	public void setUp() throws Exception
	{
		ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Login");
		loginPage = new LoginPage(driver);
		loginPage.acceptCookies();
		loginPage.clickContinueWithEmailButton();
	}

	@Test(priority = 1, description="scenario with invalid format of email and correct password")

	public void testInvalidFormatEmailAndCorrectPwd() throws Exception 
	{		
		loginPage.enterUsername(ExcelUtils.getCellData(1, 1));
		loginPage.enterPassword(ExcelUtils.getCellData(1, 2));
		loginPage.clickLoginButton();
		loginPage.verifyIncorrectFormatEmailAndCorrectPassword();
	}

	@Test(priority = 2, description="scenario with blank email and blank password")

	public void testBlankUserNameAndBlankPassword() throws Exception 
	{
		loginPage.enterUsername(ExcelUtils.getCellData(2, 1));
		loginPage.enterPassword(ExcelUtils.getCellData(2, 2));
		loginPage.clickLoginButton();
		loginPage.verifyBlankEmailOrBlankPassword();
	}

	@Test(priority = 3, description="scenario with wrong email or password")

	public void testWrongCredentials() throws Exception 
	{		
		loginPage.enterUsername(ExcelUtils.getCellData(5, 1));	
		loginPage.enterPassword(ExcelUtils.getCellData(5, 2));	
		loginPage.clickLoginButton();
		loginPage.verifyWrongCredentials();
	}

	
	@Test(priority = 4, description="scenario with correct email and correct password")

	public void testSuccessfulLogin() throws Exception 
	{
		loginPage.enterUsername(ExcelUtils.getCellData(4, 1));	
		loginPage.enterPassword(ExcelUtils.getCellData(4, 2));
		loginPage.clickLoginButton();
		loginPage.clickSendCode();
		loginPage.enterEmailVerificationCode();
		loginPage.verifySuccessfulLogin();
	}
	
	/*@Test(description="scenario with correct email and correct password")

	public void test() throws Exception 
	{
		loginPage.enterUsername(ExcelUtils.getCellData(4, 1));	
		loginPage.enterPassword(ExcelUtils.getCellData(4, 2));
		loginPage.clickLoginButton();
		loginPage.clickSendCode();
		loginPage.enterEmailVerificationCode();
		loginPage.verifySuccessfulLogin();
		Set<Cookie> cookies = driver.manage().getCookies();
	    for (Cookie cookie : cookies) {
	    	 driver.manage().deleteAllCookies(); // Clear all cookies

	         // Add the current cookie
	         driver.manage().addCookie(cookie);

	         // Refresh the page
	         driver.navigate().refresh();

	         // Check if the user is logged in (e.g., URL or specific element)
	         try {
	             if (driver.findElement(By.xpath("//span[normalize-space()='System']")).isDisplayed()) {
	                 System.out.println("Login bypassed successfully with cookie: " + cookie.getName());
	                 System.out.println("Cookie Value: " + cookie.getValue());
	                 break; // Exit once the correct cookie is found
	             }
	         } catch (NoSuchElementException e) {
	             System.out.println("Cookie " + cookie.getName() + " did not bypass login.");
	         }
	     }
	}*/
	
	
	 
	}
	
		
	


