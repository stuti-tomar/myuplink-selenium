package pageEvents;
import org.myuplink.abstractmethods.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pageObjects.HomePageElements;
import utils.WaitUtils;



public class HomePage extends WaitUtils implements HomePageElements
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath=SCHEDULE_BUTTON)
	private WebElement scheduleButton;
    
  
    @FindBy(xpath=PROFILE_BUTTON)
    private WebElement profileButton;
    
    @FindBy(xpath=SYSTEM_BUTTON)
    private WebElement systemBtn;
    
    
    @FindBy(xpath=HISTORY_bUTTON)
    private WebElement historyBtn;
    
    @FindBy(xpath=SELECT_LANGUAGE)
    private WebElement selectLanguageDropdown;
    
    public void reachToSchedulePage()
    {
    	scheduleButton.click();
    	
    }
    
    public void verifyVisibilityOfProfileIcon()
    {
    	waitForVisibilityOfElement(profileButton);
    }
    
    
    
    
    public void clickProfileBtn()
    {
    	profileButton.click();
    }
    
   public void moveToHistoryPage()
   {
	   systemBtn.click();
	   historyBtn.click();
   }
   
   public void selectLanguage() {
		Select language = new Select(selectLanguageDropdown);
		language.selectByVisibleText("English");
   }
   
   public void selectDevice() throws InterruptedException 
	{
		driver.findElement(By.className("textWrapper")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Testing simulator (R)']")).click();
		Thread.sleep(5000);
		
		

	}
}
