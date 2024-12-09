package org.myuplink.testcase;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.myuplink.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Constant;
import pageEvents.LoginPage;
import utils.ExcelUtils;
import utils.OtpFetcher;

public class LoginTest extends BaseTest {
	
	private LoginPage loginPage;
	OtpFetcher otpFetcher;
	
	
	@BeforeMethod
	public void setUp() throws Exception
	{
		ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Login");
		loginPage = new LoginPage(driver);
		loginPage.acceptCookies();
		otpFetcher=new OtpFetcher();
	}

	@Test(priority = 1, description="scenario with invalid format of email and correct password")

	public void testInvalidFormatEmailAndCorrectPwd() throws Exception 
	{
		loginPage.clickContinueWithEmailButton();
		loginPage.enterUsername(ExcelUtils.getCellData(1, 1));
		loginPage.enterPassword(ExcelUtils.getCellData(1, 2));
		loginPage.clickLoginButton();
		loginPage.verifyIncorrectFormatEmailAndCorrectPwd();
	}

	@Test(priority = 2, description="scenario with blank email and blank password")

	public void testBlankUserNameAndBlankPassword() throws Exception 
	{
		loginPage.clickContinueWithEmailButton();
		loginPage.enterUsername(ExcelUtils.getCellData(2, 1));
		loginPage.enterPassword(ExcelUtils.getCellData(2, 2));
		loginPage.clickLoginButton();
		loginPage.verifyBlankEmailAndBlankPwd();
	}

	@Test(priority = 3, description="scenario with wrong email and wrong password")

	public void testWrongCredentials() throws Exception 
	{
		loginPage.clickContinueWithEmailButton();		
		loginPage.enterUsername(ExcelUtils.getCellData(3, 1));	
		loginPage.enterPassword("whjwhbdbws");	
		loginPage.clickLoginButton();
		loginPage.verifyWrongCredentials();
	}

	
	@Test(priority = 4, description="scenario with correct email and correct password")

	public void testSuccessfulLogin() throws Exception 
	{
		loginPage.clickContinueWithEmailButton();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("rishimishra2999@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12345678Ab");
		loginPage.clickLoginButton();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@class='sendCode']")).click();
		Thread.sleep(10000);
		String otp=otpFetcher.fetchOtpFromEmail();
		driver.findElement(By.id("VerificationCode")).sendKeys(otp);
		driver.findElement(By.xpath("//button[@class='verifyCode']")).click();
		driver.findElement(By.id("continue")).click();
		Thread.sleep(5000);
		loginPage.verifySuccessfulLogin();
	}
	}
	
		
	


