package pageEvents;

import org.myuplink.abstractmethods.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.HistoryPageElements;

public class HistoryPage extends AbstractComponent implements HistoryPageElements {
	WebDriver driver;
	String currentMonthYearValue;

	public HistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = CUSTOM_TAB)
	WebElement customTab;

	@FindBy(className = CURRENT_MONTH_YEAR_VAL)
	WebElement currentMonthYearVal;

	@FindBy(xpath = CALENDAR_PREV_ICON)
	WebElement prevMonthIcon;
	
	@FindBy(xpath=CALENDAR_NEXT_ICON)
	WebElement nextMonthIcon;

	@FindBy(xpath = APPLY_DATES)
	WebElement applyDates;
	
	
	
	
	

	public void moveToCustomCalendar() 
	{
		customTab.click();
		scroll(customTab);
	}
	
	

	
	//getMonthYear function will split the current month and year shown in the calendar for custom date
	public String[] getMonthYear(String monthYearVal) 
	{
		return monthYearVal.split(" ");
	}

	//function to select start date in custom calendar
	public void selectStartDate(String expStartDay, String expStartMonth, String expStartYear) throws Exception 
	{
		if (Integer.parseInt(expStartDay) > 31) 
		{
			System.out.println("Wrong start date selected");
		}
		currentMonthYearValue =  currentMonthYearVal.getText();
		

		if (expStartMonth.equals(getMonthYear(currentMonthYearValue)[0])) 
		{
			driver.findElement(By.xpath("//button[normalize-space()='" + expStartDay + "']")).click();
		}

		else 
		{
			while (!(getMonthYear(currentMonthYearValue)[0].equals(expStartMonth)
					&& getMonthYear(currentMonthYearValue)[1].equals(expStartYear))) 
			{
				prevMonthIcon.click();
				currentMonthYearValue = currentMonthYearVal.getText();
				

			}
			try 
			{
				driver.findElement(By.xpath("//button[normalize-space()='" + expStartDay + "']")).click();
			}

			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}

	
	//function to select end date in custom calendar
	public void selectEndDate(String expEndDay, String expEndMonth, String expEndYear) throws Exception {
		if (Integer.parseInt(expEndDay) > 31) 
		{
			System.out.println("Wrong end date selected");
		}

	//	String currentMonthYearVal = driver.findElement(By.className("rdp-caption_label")).getText();
		 currentMonthYearValue=currentMonthYearVal.getText();

		if (expEndMonth.equals(getMonthYear(currentMonthYearValue)[0])) 
		{
			driver.findElement(By.xpath("//button[normalize-space()='" + expEndDay + "']")).click();
			applyDates.click();
		}

		else 
		{
			while (!(getMonthYear(currentMonthYearValue)[0].equals(expEndMonth)
					&& getMonthYear(currentMonthYearValue)[1].equals(expEndYear))) 
			{
				nextMonthIcon.click();
				currentMonthYearValue=currentMonthYearVal.getText();

			}
			try 
			{
				driver.findElement(By.xpath("//button[normalize-space()='" + expEndDay + "']")).click();
				Thread.sleep(4000);
				applyDates.click();

			}

			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}

}
