package org.myuplink.testcase;

import org.myuplink.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Constant;
import pageEvents.HistoryPage;
import pageEvents.HomePage;
import pageEvents.LoginPage;
import utils.ExcelUtils;

public class Calendar extends BaseTest {

	private LoginPage loginPage;
	private HistoryPage historyPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void reachToCalendarPage() throws Exception {
		ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Login");
		loginPage = new LoginPage(driver);
		historyPage = new HistoryPage(driver);
		homePage = new HomePage(driver);
		loginPage.acceptCookies();
		loginPage.enterUsername(ExcelUtils.getCellData(4, 1));
		loginPage.enterPassword(ExcelUtils.getCellData(4, 2));
		loginPage.clickLoginButton();
		loginPage.successfulLogin();
		// Selecting device
		homePage.selectDevice();

		// history page
		homePage.moveToHistoryPage();

		// reach to custom calendar page
		historyPage.moveToCustomCalendar();

	}
	

	@Test(priority = 1, description="selecting start date and end date with different months")

	public void setDifferentMonths() throws Exception {
		ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Custom Calendar Data");
		// calling the method to select the start date and end date
		
		//historyPage.selectStartDate(ExcelUtils.getCellData(1, 1), ExcelUtils.getCellData(1, 2), ExcelUtils.getCellData(1,3));
		//historyPage.selectEndDate(ExcelUtils.getCellData(1, 4), ExcelUtils.getCellData(1, 5), ExcelUtils.getCellData(1,6));
		historyPage.selectStartDate("21","September","2023");
		historyPage.selectEndDate("14","October","2023");
		String actual = driver.findElement(By.cssSelector(".quick-step-container")).getText();
		String exp = "21/09/2023--14/10/2023";
		Assert.assertEquals(exp, actual);
	}

	@Test(priority = 2, description="selecting start date and end date with same months")

	public void setsameMonths() throws Exception {
		ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Custom Calendar Data");
		// calling the method to select the start date and end date
		//historyPage.selectStartDate(ExcelUtils.getCellData(2, 1), ExcelUtils.getCellData(2, 2), ExcelUtils.getCellData(2,3));
		//historyPage.selectEndDate(ExcelUtils.getCellData(2, 4), ExcelUtils.getCellData(2, 5), ExcelUtils.getCellData(2,6));

		historyPage.selectStartDate("19","September","2023");
		historyPage.selectEndDate("23","September","2023");
		String actual = driver.findElement(By.cssSelector(".quick-step-container")).getText();
		String exp = "19/09/2023--23/09/2023";
		Assert.assertEquals(exp, actual);
	}
	
	@Test(priority = 3, description="selecting disabled date")

	public void setdisableddate() throws Exception {
		//ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Custom Calendar Data");
		// calling the method to select the start date and end date
		//historyPage.selectStartDate(ExcelUtils.getCellData(2, 1), ExcelUtils.getCellData(2, 2), ExcelUtils.getCellData(2,3));
		//historyPage.selectEndDate(ExcelUtils.getCellData(2, 4), ExcelUtils.getCellData(2, 5), ExcelUtils.getCellData(2,6));

		WebElement ele=driver.findElement(By.xpath("//button[normalize-space()='" + 18 + "']"));
		ele.click();
		if(ele.isEnabled())
		{
			System.out.println("enabled date selected");
		}
		else
		{
			System.out.print("Disabled date");
		}
	}

}
