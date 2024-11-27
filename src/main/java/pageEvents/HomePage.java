package pageEvents;
import org.myuplink.abstractmethods.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.HomePageElements;



public class HomePage extends AbstractComponent implements HomePageElements
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
    private WebElement profileBtn;
    
    @FindBy(xpath=SYSTEM_BUTTON)
    private WebElement systemBtn;
    
    
    @FindBy(xpath=HISTORY_bUTTON)
    private WebElement historyBtn;
    
    public void reachToSchedulePage()
    {
    	scheduleButton.click();
    	
    }
    
    
    
    
    public void clickProfileBtn()
    {
    	profileBtn.click();
    }
    
   public void moveToHistoryPage()
   {
	   systemBtn.click();
	   historyBtn.click();
   }
   
   public void handlinSavePassword()
   {
	   
   }
   
   public void selectDevice() throws InterruptedException 
	{
		driver.findElement(By.className("textWrapper")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Testing simulator (R)']")).click();
		Thread.sleep(5000);
		
		

	}
}
