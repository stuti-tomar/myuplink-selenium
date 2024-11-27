package pageEvents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.ProfileSettingElements;

public class ProfileSettingPage implements ProfileSettingElements
{

    WebDriver driver;
    
    public ProfileSettingPage(WebDriver driver) 
    {
        this.driver = driver;
    }
    
    @FindBy(xpath="PROFILESETTINGBTN")
    private WebElement profileSettingBtn;
    
    public void clickProfileSettingButton()
    {
    	profileSettingBtn.click();
    }
    
    public void scrollToProfileSetting()
    {
    	WebElement l=driver.findElement(By.xpath("//h1[@class='titleComponent']//span[contains(text(),'Profile Settings')]"));
        
        
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
    }
	
}
