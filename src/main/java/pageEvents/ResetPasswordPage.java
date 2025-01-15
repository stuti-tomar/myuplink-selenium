package pageEvents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pageObjects.ResetPasswordElements;

public class ResetPasswordPage implements ResetPasswordElements
{

    public WebDriver driver;
    
    public ResetPasswordPage(WebDriver driver) 
    {
        this.driver = driver;
       PageFactory.initElements(driver, this);
    }
    
  
    @FindBy(xpath=RESET_PWD_BUTTON)
    private WebElement ResetPwdBtn;
    
    @FindBy(xpath=RESET_EMAIL)
    private WebElement resetEmail;
    
    
    public void enterResetEmail(String email)
    {
    	resetEmail.sendKeys(email);
    }
    
    public void clickResetPasswordButton()
    {
    	ResetPwdBtn.click();
    }
    
    
    public void verifyEmptyResetEmail()
    { 
    String expected="E-mail is a mandatory field";
    String actual=driver.findElement(By.xpath("(//p[@class='inputComponent__error'])[1]")).getText();
    Assert.assertEquals(expected, actual);
    if(expected.equalsIgnoreCase(actual))
    {
 	   System.out.println("Error message verified:"+expected);
    }
    }
    
    public void verifyWrongFormatResetEmail()
    { 
    //String expected="E-mail is not valid";
   WebElement actual=driver.findElement(By.xpath("//p[@class='inputComponent__error']"));
    Assert.assertEquals(actual.getText(),"E-mail is not valid","Strings are diff");
    }
   /* if(expected.equalsIgnoreCase(actual))
    {
 	   System.out.println("Error message verified:"+expected);
    }
    }*/
    
    public void verifyMsgWithCorrectResetEmail()
    { 
    String expected="If a matching account is found, you will receive an email with instructions to reset your password.";
    String actual=driver.findElement(By.xpath("(//span[contains(text(),'If a matching account is found, you will receive a')])[1]")).getText();
    Assert.assertEquals(expected, actual);
    if(expected.equalsIgnoreCase(actual))
    {
 	   System.out.println("Info message verified:"+expected);
    }
    }
	
}
