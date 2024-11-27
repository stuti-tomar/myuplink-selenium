package pageEvents;

import java.time.Duration;
import java.util.List;

import org.myuplink.abstractmethods.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.DynamicLocators;
import pageObjects.SchedulePageElements;

public class SchedulePage extends AbstractComponent implements SchedulePageElements {
	WebDriver driver;
	WebDriverWait wait;
	

	public SchedulePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		

	}

	@FindBy(id = EDIT_MODES)
	private WebElement editModes;

	@FindBy(xpath = WEEKDAYS)
	private List<WebElement> weekDays;

	@FindBy(xpath = MODES)
	private List<WebElement> modeNamesList;

	@FindBy(xpath = NEXTBUTTON)
	private WebElement next;

	@FindBy(xpath = BACKBUTTON)
	private WebElement backButton;

	@FindBy(xpath = SAVEBUTTON)
	private WebElement saveButton;

	@FindBy(xpath = YESBUTTON)
	private WebElement yesButton;

	@FindBy(xpath = DROPDOWN_BUTTON_START_HOURS)
	private WebElement dropdownButtonStartHours;

	@FindBy(xpath = DROPDOWN_LIST_START_HOURS)
	private List<WebElement> dropdownListStartHours;

	@FindBy(xpath = DROPDOWN_BUTTON_START_MINUTES)
	private WebElement dropdownButtonStartMinutes;

	@FindBy(xpath = DROPDOWN_LIST_START_MINUTES)
	private List<WebElement> dropdownListStartMinutes;

	@FindBy(xpath = DROPDOWN_BUTTON_END_HOURS)
	private WebElement dropdownButtonEndHours;

	@FindBy(xpath = DROPDOWN_LIST_END_HOURS)
	private List<WebElement> dropdownListEndHours;

	@FindBy(xpath = DROPDOWN_BUTTON_END_MINUTES)
	private WebElement dropdownButtonEndMinutes;

	@FindBy(xpath = DROPDOWN_LIST_END_MINUTES)
	private List<WebElement> dropdownListEndMinutes;

	@FindBy(xpath = DELETE_EVENT_BUTTON)
	private WebElement deleteEventButton;

	@FindBy(xpath = COPY_BUTTON)
	private WebElement copyButton;

	public void scrollToSchedule() {
		scroll(editModes);
	}

	public void addScheduleEvent(String weekDay, String desiredModeName) throws InterruptedException {

		WebElement addButton = driver.findElement(By.xpath(DynamicLocators.getAddButton(weekDay)));
		scroll(addButton);
		addButton.click();

		boolean EventNameFound = false;

		for (int j = 0; j < modeNamesList.size(); j++) {
			String modeName = modeNamesList.get(j).getText();
			if (modeName.equals(desiredModeName)) {
				EventNameFound = true;
				modeNamesList.get(j).click();
			}
		}

		next.click();

		if (EventNameFound == false) {
			Assert.fail("Event '" + desiredModeName + "' not found in the event addition list.");
		}
	}

	public void addScheduleEventTime(String startHour, String startMinute, String endHour, String endMinute)
			throws InterruptedException {
		// selectStartHour
		dropdownButtonStartHours.click();

		for (int i = 1; i < dropdownListStartHours.size(); i++) {
			String hour = dropdownListStartHours.get(i).getText();
			if (hour.equals(startHour)) {
				dropdownListStartHours.get(i).click();
			}
		}

		// selectStartMinute
		dropdownButtonStartMinutes.click();

		for (int i = 1; i < dropdownListStartMinutes.size(); i++) {
			String hour = dropdownListStartMinutes.get(i).getText();
			if (hour.equals(startMinute)) {
				dropdownListStartMinutes.get(i).click();
			}
		}

		// selectEndHour
		dropdownButtonEndHours.click();

		for (int i = 1; i < dropdownListEndHours.size(); i++) {
			String hour = dropdownListEndHours.get(i).getText();
			if (hour.equals(endHour)) {
				dropdownListEndHours.get(i).click();
			}
		}

		// selectEndMinute
		dropdownButtonEndMinutes.click();

		for (int i = 1; i < dropdownListEndMinutes.size(); i++) {
			String hour = dropdownListEndMinutes.get(i).getText();
			if (hour.equals(endMinute)) {
				dropdownListEndMinutes.get(i).click();
			}
		}

		saveButton.click();

		try {
			if (yesButton.isDisplayed()) {
				yesButton.click();
			}
		} catch (NoSuchElementException e) {
		}
	}

	public void verifyAddedScheduleEvent(String weekDay, String desiredModeName, String desiredStartTime,
			String desiredEndTime) throws InterruptedException {
		Thread.sleep(2000);

		String modeNameXPath = DynamicLocators.getModeNameXPath(weekDay, desiredModeName);
		String startTimeXPath = DynamicLocators.getStartTimeXPath(weekDay, desiredModeName);
		String endTimeXPath = DynamicLocators.getEndTimeXPath(weekDay, desiredModeName);

		List<WebElement> modeNameElements = driver.findElements(By.xpath(modeNameXPath));
		List<WebElement> startTimeElements = driver.findElements(By.xpath(startTimeXPath));
		List<WebElement> endTimeElements = driver.findElements(By.xpath(endTimeXPath));

		// Checking of getting the matching elements
		Assert.assertTrue(modeNameElements.size() > 0, "Mode name '" + desiredModeName + "' not found on " + weekDay);
		Assert.assertTrue(startTimeElements.size() > 0,
				"Start time for '" + desiredModeName + "' not found on " + weekDay);
		Assert.assertTrue(endTimeElements.size() > 0, "End time for '" + desiredModeName + "' not found on " + weekDay);

		boolean nameEventFound = false;
		boolean timeEventFound = false;

		for (int i = 0; i < modeNameElements.size(); i++) {
			String actualModeName = modeNameElements.get(i).getText();
			if (actualModeName.equals(desiredModeName)) {
				nameEventFound = true;

				String actualStartTime = startTimeElements.get(i).getText();
				String actualEndTime = endTimeElements.get(i).getText().replace("- ", "");

				if (actualStartTime.equals(desiredStartTime) && actualEndTime.equals(desiredEndTime)) {
					timeEventFound = true;
					break;
				}
			}
		}

		Assert.assertTrue(nameEventFound, "Event " + desiredModeName + " not found on " + weekDay);

		Assert.assertTrue(timeEventFound, "Event Time " + desiredStartTime + " to " + desiredEndTime + " not found on "
				+ weekDay + " for " + desiredModeName);

	}

	public void copyEventWeekdayData(String sourceWeekDay, String destinationWeekDay) throws InterruptedException {
		
		if(sourceWeekDay.equals(destinationWeekDay))
		{
			throw new AssertionError("Data cannot be copied into same day");
		}
		WebElement sourceDay = driver.findElement(By.xpath(DynamicLocators.getSouceDayCopyButton(sourceWeekDay)));
		sourceDay.click();
		Thread.sleep(3000);
		WebElement destinationDay = driver.findElement(By.xpath(DynamicLocators.getDestinationDayToBeCopied(destinationWeekDay)));
		destinationDay.click();
		copyButton.click();
	}

	public void verifyScheduleCopiedData(String sourceWeekDay, String destinationWeekDay) throws InterruptedException {  
		
		
		List<WebElement> sourceDayData = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(DynamicLocators.getSourceDayData(sourceWeekDay))));
		
		List<WebElement> destinationDayData = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(DynamicLocators.getDestinationDayData(destinationWeekDay))));
		
		List<WebElement> sourceStartTime = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(DynamicLocators.getSourceDayStartTime(sourceWeekDay))));
		List<WebElement> sourceEndTime = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(DynamicLocators.getSourceDayEndTime(sourceWeekDay))));

		List<WebElement> destinationStartTime = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(DynamicLocators.getDestinationDayStartTime(destinationWeekDay))));
	    List<WebElement> destinationEndTime = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(DynamicLocators.getDestinationDayEndTime(destinationWeekDay))));
		
		
		 if (sourceDayData.size() != destinationDayData.size() || sourceStartTime.size() != destinationStartTime.size()
		            || sourceEndTime.size() != destinationEndTime.size()) {
		        throw new AssertionError("Source and destination data sizes do not match.");
		    }
		 
		 for (int i = 0; i < sourceDayData.size(); i++) {
		       String sourceModeName = sourceDayData.get(i).getText();
		        Thread.sleep(2000);
		        String sourceStartTimeText = sourceStartTime.get(i).getText();
		        Thread.sleep(2000);
		        String sourceEndTimeText = sourceEndTime.get(i).getText();
		        Thread.sleep(2000);
		        String destinationModeName = destinationDayData.get(i).getText();
		        Thread.sleep(2000);
		        String destinationStartTimeText = destinationStartTime.get(i).getText();
		        Thread.sleep(2000);
		        String destinationEndTimeText = destinationEndTime.get(i).getText();
		        Thread.sleep(2000); 
			 

		        if (!sourceModeName.equals(destinationModeName)) {
		            throw new AssertionError("Mode names do not match for " + sourceModeName);
		        }
		        if (!sourceStartTimeText.equals(destinationStartTimeText)) {
		            throw new AssertionError("Start times do not match for " + sourceModeName);
		        }
		        if (!sourceEndTimeText.equals(destinationEndTimeText)) {
		            throw new AssertionError("End times do not match for " + sourceModeName);
		        }

		
		}
	}
	
	public void deleteScheduleEvent()
	{
		int count=0;
		List<WebElement> modeListStartTime=driver.findElements(By.xpath("//div[@class='device-scheduling__item'][h3/span='Monday']/following-sibling::div[@class='timeslots'][1]//div[normalize-space()='testing']/following-sibling::div[@class='timeslot-time'][1]"));
		List <WebElement> modeListEndTime=driver.findElements(By.xpath("//div[@class='device-scheduling__item'][h3/span='Monday']/following-sibling::div[@class='timeslots'][1]//div[normalize-space()='testing']/following-sibling::div[@class='timeslot-time'][2]"));
				
		for(int i=0; i<modeListStartTime.size();i++)
		{
			String modeStartTime = modeListStartTime.get(i).getText();
			String modeEndTime = modeListEndTime.get(i).getText().replace("- ", "");
			
			if(modeStartTime.equals("00:00")&& modeEndTime.equals("06:00"))
			{
				count+=1;
				modeListStartTime.get(i).click();
				driver.findElement(By.xpath("//button[normalize-space()='Delete event']")).click();
			}
		}
		if(count==0)
		{
			throw new AssertionError("Mode not found");
		}
				
				//day modes
		//div[@class='device-scheduling__item'][h3/span='Sunday']/following-sibling::div[@class='timeslots'][1]//div[@class='timeslot-name']
		
		
		//mode wise start time and end time
		//start time
		//div[@class='device-scheduling__item'][h3/span='Sunday']/following-sibling::div[@class='timeslots'][1]//div[normalize-space()='testyyy']/ancestor::div/following-sibling::div[@class='timeslot'][2]//div[@class='timeslot-time'][1]
		
		
		//end time
		//div[@class='device-scheduling__item'][h3/span='Sunday']/following-sibling::div[@class='timeslots'][1]//div[normalize-space()='testyyy']/ancestor::div/following-sibling::div[@class='timeslot'][2]//div[@class='timeslot-time'][2]
	}
}
