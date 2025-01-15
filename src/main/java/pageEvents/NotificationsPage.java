package pageEvents;

import org.myuplink.abstractmethods.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageObjects.NotificationsElements;

public class NotificationsPage extends AbstractComponent implements NotificationsElements {
	WebDriver driver;

	public NotificationsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = GENERAL_MENU)
	private WebElement generalMenu;

	@FindBy(xpath = NOTIFICATION_MENU)
	private WebElement notificationMenu;

	@FindBy(xpath = NOTIFICATION_INBOX)
	private WebElement notificationInbox;

	@FindBy(xpath = NO_NOTIFICATION_INFO)
	private WebElement noNotificationMsg;

	@FindBy(id = PROFILE_ICON)
	private WebElement profileIcon;

	@FindBy(xpath = PROFILE_SETTINGS)
	private WebElement profileSettings;

	@FindBy(xpath = NOTIFICATION_TAB)
	private WebElement notificationTab;

	@FindBy(xpath = TOGGLE_TEXT_OFF)
	private WebElement toggleTextOff;

	@FindBy(xpath = TOGGLE_TEXT_ON)
	private WebElement toggleTextOn;

	@FindBy(xpath = TOGGLE_BUTTON)
	private WebElement toggleButton;

	public void reachNotificationTab() throws InterruptedException {
		profileIcon.click();
		profileSettings.click();
		Thread.sleep(3000);
		scroll(notificationTab);
		notificationTab.click();
		Thread.sleep(3000);

	}

	public void disableNotifications() throws InterruptedException {
	    WebElement toggleButton = driver.findElement(By.xpath("//div[@class='toggle-switch-wrapper']"));
	    
	    boolean b1=toggleButton.isSelected();
	    System.out.println(b1);
	    driver.findElement(By.xpath("(//label[@class='switch'])[1]")).click();
	    Thread.sleep(3000);
	    boolean b2=toggleButton.isSelected();
	    System.out.println(b2);
	    
	  /*  driver.findElement(By.xpath("(//label[@class='switch'])[1]")).click();
	    Boolean toggleStatus = toggleButton.isSelected();
	    System.out.println(toggleStatus);
	    
	    
	    
	    if (toggleStatus) {
	        // If notifications are already disabled, print a message
	        System.out.println("Notifications are already disabled.");
	    } else {
	        // If notifications are currently enabled, click the toggle button to disable them
	        driver.findElement(By.xpath("(//label[@class='switch'])[1]")).click();
	        System.out.println("Notifications are now disabled.");
	    }*/
	}

	 public void optNotifications() 
	 {
		 WebElement element1=driver.findElement(By.xpath("(//div[contains(@class,'checkboxComponent')])[1]"));
 		boolean b1=element1.isSelected();
 		System.out.println(b1);
 		
 		WebElement element2=driver.findElement(By.xpath("(//div[@class='checkboxComponent ']/child::label) [2]"));
 		boolean b2=element2.isSelected();
 		System.out.println(b2);
	 }

}