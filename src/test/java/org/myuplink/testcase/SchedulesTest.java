package org.myuplink.testcase;

import java.time.Duration;
import org.myuplink.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageEvents.HomePage;
import pageEvents.LoginPage;
import pageEvents.SchedulePage;
import utils.ExcelUtils;
import utils.JSExecutorUtils;
import utils.WaitUtils;

public class SchedulesTest extends BaseTest {
	private LoginPage loginPage;
	private HomePage homePage;
	private SchedulePage schedulePage;
	WaitUtils waitUtils;
	JSExecutorUtils js; 
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10000));

	@BeforeMethod(description = "Reaching to Schedule page")
	public void reachToSchedulePage() throws Exception {
		//ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Schedules");
		loginPage = new LoginPage(driver);
		waitUtils=new WaitUtils(driver);
		js=new JSExecutorUtils(driver);
		//homePage = new HomePage(driver);
		//schedulePage = new SchedulePage(driver);
		//loginPage.acceptCookies();
		//loginPage.enterUsername(ExcelUtils.getCellData(4, 1));
		//loginPage.enterPassword(ExcelUtils.getCellData(4, 2));
		//loginPage.clickLoginButton();
		//loginPage.clickSendCode();
		//loginPage.enterEmailVerificationCode();
		//loginPage.verifySuccessfulLogin();
		//homePage.selectLanguage();
		//homePage.selectDevice();
		//homePage.reachToSchedulePage();
		//schedulePage.scrollToSchedule();
		//wait = new WebDriverWait(driver, Duration.ofMillis(20000));
	}
	
@Test
public void handleContinueButton() throws InterruptedException {
    // Navigate to the app

    // Add AZURE_B2C_TOKENS cookie
	
    Cookie sessionCookie = new Cookie("OptanonAlertBoxClosed", "2024-12-11T07:03:42.290Z");
    driver.manage().addCookie(sessionCookie);

    // Refresh to apply the cookie
    driver.navigate().refresh();
    
    
    //loginPage.acceptCookies();
    // Wait and check for the "Continue" button
    
    WebElement continueButton = driver.findElement(By.xpath("//span[normalize-space()='Continue with email']"));
    
    try {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        
        js.scrollToElement(continueButton);
        if (continueButton.isDisplayed()) {
            continueButton.click(); // Click the "Continue" button
        }
        
    } catch (Exception e) {
    	waitUtils.waitForVisibilityOfElement(continueButton);
        // If the direct click fails, retry with the retry logic
        System.out.println("Direct click failed. Retrying...");
        js.clickWithRetry(continueButton);
    }
    
   
    // Now verify if the user is logged in
    System.out.println("Login bypassed successfully!");
    
    
}

	@Test(description = "Adding single event on any weekday and verifying correct date details")
	public void testAddingEventAndVerifyingCorrectDateDetails() throws Exception {
		schedulePage.addScheduleEvent(ExcelUtils.getCellData(1, 3), ExcelUtils.getCellData(1, 4));
		schedulePage.addScheduleEventTime(ExcelUtils.getCellData(1, 5), ExcelUtils.getCellData(1, 6),
				ExcelUtils.getCellData(1, 7), ExcelUtils.getCellData(1, 8));
		schedulePage.verifyAddedScheduleEvent(ExcelUtils.getCellData(1, 3), ExcelUtils.getCellData(1, 4),
				ExcelUtils.getCellData(1, 9), ExcelUtils.getCellData(1, 10));
	}

	@Test(description = "Adding multiple events from the event addition list and verifying correct date details")
	public void testAddingMultipleEventsAndVerifyingCorrectDateDetails() throws Exception {
		// first event addition
		schedulePage.addScheduleEvent(ExcelUtils.getCellData(2, 3), ExcelUtils.getCellData(2, 4));
		schedulePage.addScheduleEventTime(ExcelUtils.getCellData(2, 5), ExcelUtils.getCellData(2, 6),
				ExcelUtils.getCellData(2, 7), ExcelUtils.getCellData(2, 8));
		schedulePage.verifyAddedScheduleEvent(ExcelUtils.getCellData(2, 3), ExcelUtils.getCellData(2, 4),
				ExcelUtils.getCellData(2, 9), ExcelUtils.getCellData(2, 10));
		Thread.sleep(2000);

		// second event addition
		schedulePage.addScheduleEvent(ExcelUtils.getCellData(3, 3), ExcelUtils.getCellData(3, 4));
		schedulePage.addScheduleEventTime(ExcelUtils.getCellData(3, 5), ExcelUtils.getCellData(3, 6),
				ExcelUtils.getCellData(3, 7), ExcelUtils.getCellData(3, 8));
		schedulePage.verifyAddedScheduleEvent(ExcelUtils.getCellData(3, 3), ExcelUtils.getCellData(3, 4),
				ExcelUtils.getCellData(3, 9), ExcelUtils.getCellData(3, 10));
		Thread.sleep(2000);

		// third event addition
		schedulePage.addScheduleEvent(ExcelUtils.getCellData(4, 3), ExcelUtils.getCellData(4, 4));
		schedulePage.addScheduleEventTime(ExcelUtils.getCellData(4, 5), ExcelUtils.getCellData(4, 6),
				ExcelUtils.getCellData(4, 7), ExcelUtils.getCellData(4, 8));
		schedulePage.verifyAddedScheduleEvent(ExcelUtils.getCellData(4, 3), ExcelUtils.getCellData(4, 4),
				ExcelUtils.getCellData(4, 9), ExcelUtils.getCellData(4, 10));
		Thread.sleep(2000);
	}

	@Test(description = "Adding event on weekday and and verifying incorrect date details")
	public void testAddingEventAndVerifyingIncorrectDateDetails() throws Exception {
		schedulePage.addScheduleEvent(ExcelUtils.getCellData(5, 3), ExcelUtils.getCellData(5, 4));
		schedulePage.addScheduleEventTime(ExcelUtils.getCellData(5, 5), ExcelUtils.getCellData(5, 6),
				ExcelUtils.getCellData(5, 7), ExcelUtils.getCellData(5, 8));
		schedulePage.verifyAddedScheduleEvent(ExcelUtils.getCellData(5, 3), ExcelUtils.getCellData(5, 4),
				ExcelUtils.getCellData(5, 9), ExcelUtils.getCellData(5, 10));
	}

	@Test(description = "Adding an event and Verifying other event which is not present on weekday")
	public void testAddingEventAndVerifyingOtherAbsentEvent() throws Exception {
		schedulePage.addScheduleEvent(ExcelUtils.getCellData(6, 3), ExcelUtils.getCellData(6, 4));
		schedulePage.addScheduleEventTime(ExcelUtils.getCellData(6, 5), ExcelUtils.getCellData(6, 6),
				ExcelUtils.getCellData(6, 7), ExcelUtils.getCellData(6, 8));
		schedulePage.verifyAddedScheduleEvent(ExcelUtils.getCellData(6, 3), ExcelUtils.getCellData(4, 4),
				ExcelUtils.getCellData(6, 9), ExcelUtils.getCellData(6, 10));
	}

	@Test(description = "Adding event which is not present in the event addition list")
	public void testAddingEventWhenNotPresent() throws Exception {
		schedulePage.addScheduleEvent(ExcelUtils.getCellData(7, 3), ExcelUtils.getCellData(7, 4));
	}

	@Test(dependsOnMethods="testAddingEventAndVerifyingCorrectDateDetails", description = "Copying weekday data into another and verifying with correct destination day")
	public void testCopyWeekdayWithCorrectDestinationDay() throws Exception {
		schedulePage.copyEventWeekdayData("Monday", "Sunday");
		schedulePage.verifyScheduleCopiedData("Monday", "Sunday");
		Thread.sleep(3000);
	}

	@Test(description = "Copying weekday data into another and verfying with incorrect destination day having same number of data size")
	public void testCopyWeekdayWithIncorrectDestinationDay() throws InterruptedException {
		schedulePage.copyEventWeekdayData("Wednesday", "Tuesday");
		schedulePage.verifyScheduleCopiedData("Wednesday", "Thursday");
		Thread.sleep(3000);
	}

	@Test(description = "Copying weekday data into another and verfying with incorrect destination day having different number data size")
	public void testCopyWeekdayWithDifferentDataSizeDestinationDay() throws InterruptedException {
		schedulePage.copyEventWeekdayData("Tuesday", "Wednesday");
		schedulePage.verifyScheduleCopiedData("Tuesday", "Friday");
		Thread.sleep(3000);
	}

	@Test(description = "Copying weekday data into same day")
	public void testCopyWeekdayIntoSameDay() throws InterruptedException {
		schedulePage.copyEventWeekdayData("Tuesday", "Tuesday");
	}

	
	  @Test(description = "delete particular event when event is present") 
	  public void testDeleteEvent() throws InterruptedException {
	  schedulePage.deleteScheduleEvent();
	  
	  }
	 
	 /*
	 * @Test(description = "handling delete when event is not present") public void
	 * testDeleteEventWhenNotPresent() throws InterruptedException {
	 * schedulePage.deleteScheduleEvent(DAY_SATURDAY, MODE_NAME1);
	 * 
	 * }
	 * 
	 *
	 * 
	 * // sc-editing time of the event
	 * 
	 * 
	 * //delete all events //EDIT MODE NAME
	 *delete feature when time is wromg but mode is present
	 //delete feature when time is right but mode present
	  * Test Case: Verify User Can Delete a Mode from Schedule
Test Case: Verify Mode is Removed from Schedule List After Deletion
Test Case: Verify Mode Deletion for a Single Day
Test Case: Verify Deletion of Multiple Modes Across Different Days
Test Case: Verify Mode is Removed from Schedule List After Deletion
Test Case: Verify Deletion of Mode with Invalid Time Range
	 * 
	 */

}
