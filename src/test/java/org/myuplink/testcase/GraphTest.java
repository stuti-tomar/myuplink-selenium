package org.myuplink.testcase;
import org.myuplink.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Constant;
import pageEvents.GraphPage;
import pageEvents.HomePage;
import pageEvents.LoginPage;
import utils.ExcelUtils;

public class GraphTest extends BaseTest 
{
	private LoginPage loginPage;
	private HomePage homePage;
	private GraphPage graphPage;
	

	
	@BeforeMethod(description="Reaching to History page to access the graph")
	public void reachToCalendarPage() throws Exception 
	{
		ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Login");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		graphPage=new GraphPage(driver);
		loginPage.acceptCookies();
		loginPage.enterUsername(ExcelUtils.getCellData(4, 1));
		loginPage.enterPassword(ExcelUtils.getCellData(4, 2));
		loginPage.clickLoginButton();
		loginPage.verifySuccessfulLogin();
		// Selecting device
		homePage.selectDevice();
		// history page
		homePage.moveToHistoryPage();
		
	}
	
	
	@Test (priority=1, description="Fetching graph data of particular months")
	public void graphYearHandling() throws InterruptedException
	{
		
		graphPage.reachToYearGraphPage();
		graphPage.addParameter();
		graphPage.verifyYearGraphData("February", "August", "December");
		
	}
	
	@Test(priority=2, description="add more than maxiumum allowed parameters")
	public void exceedSelectionOfMaximumParameters() throws InterruptedException
	{
		graphPage.addMaxParameters();
		graphPage.addMoreThanTenParamters();
		
	}

	@Test(priority=3, description="verify clear all feature for parameter addition")
	public void clearAll() throws InterruptedException
	{
		
		graphPage.addMaxParameters();
		graphPage.addMoreThanTenParamters();
		driver.findElement(By.xpath("//span[normalize-space()='Select']")).click();
		driver.findElement(By.cssSelector(".clear-value-btn")).click();
		Thread.sleep(3000);
		WebElement h=driver.findElement(By.cssSelector(".chart-list-container"));
		Thread.sleep(4000);
		Boolean i=h.isDisplayed();
		System.out.println(i);
		Assert.assertFalse(i);
	}
	
}


	
		
	
	
	
	


































	