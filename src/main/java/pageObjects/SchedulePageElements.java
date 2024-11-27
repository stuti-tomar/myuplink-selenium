package pageObjects;

public interface SchedulePageElements {
	
	String EDIT_MODES="edit-modes";
	String WEEKDAYS = "//div[@class='device-scheduling__item']//h3";
	String MODES="//button[@class='button button--info']";
	String NEXTBUTTON="//span[normalize-space()='Next']";
	String BACKBUTTON="//span[normalize-space()='Back']";
	String SAVEBUTTON="//span[normalize-space()='Save']";
	String YESBUTTON="//span[normalize-space()='Yes']";
	
	//Event time components
	String DROPDOWN_BUTTON_START_HOURS="(//span[@class='caret'])[5]";
	String DROPDOWN_LIST_START_HOURS="(//ul[@role='menu'])[2] //a";
	
	String DROPDOWN_BUTTON_START_MINUTES="(//span[@class='caret'])[6]";
	String DROPDOWN_LIST_START_MINUTES="(//ul[@role='menu'])[3] //a";
	
	String DROPDOWN_BUTTON_END_HOURS="(//span[@class='caret'])[7]";
	String DROPDOWN_LIST_END_HOURS="(//ul[@role='menu'])[4] //a";
	
	String DROPDOWN_BUTTON_END_MINUTES="(//span[@class='caret'])[8]";
	String DROPDOWN_LIST_END_MINUTES="(//ul[@role='menu'])[5] //a";
	
	String DELETE_EVENT_BUTTON="//button[@class='button button--default delete-event']";
	
	String COPY_BUTTON="//button[@type='submit']";
	
	
	
		
}
