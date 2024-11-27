package pageObjects;

public class DynamicLocators {

	public static String getBaseXPath(String weekDay) {
		return "//div[@class='device-scheduling__item'][h3/span='" + weekDay
				+ "']/following-sibling::div[@class='timeslots'][1]";
	}

	public static String getModeNameXPath(String weekDay, String desiredModeName) {
		return getBaseXPath(weekDay) + "//div[@class='timeslot-name' and normalize-space()='" + desiredModeName + "']";
	}

	public static String getStartTimeXPath(String weekDay, String desiredModeName) {
		return getBaseXPath(weekDay) + "//div[@class='timeslot-name' and normalize-space()='" + desiredModeName
				+ "']/following-sibling::div[@class='timeslot-time'][1]";
	}

	public static String getEndTimeXPath(String weekDay, String desiredModeName) {
		return getBaseXPath(weekDay) + "//div[@class='timeslot-name' and normalize-space()='" + desiredModeName
				+ "']/following-sibling::div[@class='timeslot-time'][2]";
	}

	public static String getAddButton(String weekDay) {
		return "//h3[span[text()='" + weekDay
				+ "']]/following-sibling::div[@class='icons']/div[@class='icon']/button[@class='button button--round']";
	}

	public static String getSouceDayCopyButton(String weekDay) {
		return "//h3[span[text()='" + weekDay
				+ "']]/following-sibling::div[@class='icons']/div[@class='icon']/button[@class='button button--round icon--default']";
	}
	
	public static String getDestinationDayToBeCopied(String destinationWeekDay) {
		return "//div[@class='day-selector-item']//span[contains(text(),'"+destinationWeekDay+"')]";

	}
	
	public static String getSourceDayData(String sourceWeekDay) {
	return "//div[@class='device-scheduling__item'][h3/span='" + sourceWeekDay
	+ "']/following-sibling::div[@class='timeslots'][1]//div[@class='timeslot-name']";
	}
	
	public static String getDestinationDayData(String destinationWeekDay) {
		return "//div[@class='device-scheduling__item'][h3/span='" + destinationWeekDay+
				"']/following-sibling::div[@class='timeslots'][1]//div[@class='timeslot-name']";
		}

	public static String getSourceDayStartTime(String sourceWeekDay) {
		return "//div[@class='device-scheduling__item'][h3/span='"+ sourceWeekDay + "']/following-sibling::div[@class='timeslots'][1]//div[@class='timeslot-name']/following-sibling::div[@class='timeslot-time'][1]";
		

	}
	
	public static String getSourceDayEndTime(String sourceWeekDay) {
		return "(//div[@class='device-scheduling__item'][h3/span='"+ sourceWeekDay+"']/following-sibling::div[@class='timeslots'][1])//div[@class='timeslot-name']/following-sibling::div[@class='timeslot-time'][2]";

	}
	
	public static String getDestinationDayStartTime(String destinationWeekDay) {
		return "//div[@class='device-scheduling__item'][h3/span='"+ destinationWeekDay + "']/following-sibling::div[@class='timeslots'][1]//div[@class='timeslot-name']/following-sibling::div[@class='timeslot-time'][1]";
		

	}
	
	public static String getDestinationDayEndTime(String destinationWeekDay) {
		return "(//div[@class='device-scheduling__item'][h3/span='"+ destinationWeekDay+"']/following-sibling::div[@class='timeslots'][1])//div[@class='timeslot-name']/following-sibling::div[@class='timeslot-time'][2]";

	}
	
	
	
	
}
