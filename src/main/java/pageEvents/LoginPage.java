package pageEvents;
import org.myuplink.abstractmethods.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageObjects.LoginElements;
import pageObjects.HomePageElements;



public class LoginPage extends AbstractComponent implements LoginElements
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
    @FindBy(id = ACCEPT_COOKIES_BUTTON)
    private WebElement acceptCookiesButton;
    
    @FindBy(xpath=FORGOT_PASSWORD_LINK)
    private WebElement ForgotPwdBtn;
    
    
    @FindBy(xpath = USERNAME)
    private WebElement usernameField;
    
    @FindBy(xpath=PASSWORD)
    private WebElement passwordField;
    
    @FindBy(id = WRONG_EMAIL_MSG)
    private WebElement wrongEmailFormatMsg;
    
    @FindBy(xpath = LOGIN_BTN)
    private WebElement loginButton;
    
    @FindBy(xpath=WRONG_CRED_MSG)
 	private WebElement wrongCredentialPopup; 
    
    @FindBy(xpath=HomePageElements.PROFILE_BUTTON)
	WebElement profileIcon;
   
    
    public void acceptCookies() 
    {
    	click(acceptCookiesButton);
    	
    }
    
    public void clickForgotPwdBtn()
    {
    	ForgotPwdBtn.click();
    }

    public void enterUsername(String username) 
    {
    	usernameField.sendKeys(username);
    }

    public void enterPassword(String password) 
    {
    	passwordField.sendKeys(password);
    }

    public void clickLoginButton() 
    {
    	loginButton.click();
    }
    
    public void verifyIncorrectFormatEmailAndCorrectPwd() 
    {
		String expected = "Username should be an e-mail address";
		String actual=driver.findElement(By.className("inputComponent__error")).getText();
		Assert.assertEquals(expected, actual);
		if (expected.equalsIgnoreCase(actual)) {
			System.out.println("Error message verified :" + expected);
			
		}
	}
    
    public void verifyBlankEmailAndBlankPwd() 
    {
		String expectedUsernm = "Username is mandatory field";
		String actualUsernm=driver.findElement(By.xpath("//p[normalize-space()='Username is mandatory field']")).getText();
		
		String expectedPwd = "Password is a mandatory field";
		String actualPwd=driver.findElement(By.xpath("//p[normalize-space()='Password is a mandatory field']")).getText();
		Assert.assertEquals(expectedUsernm, actualUsernm);
		if (actualUsernm.equalsIgnoreCase(actualUsernm)) 
		{
			if (expectedPwd.equalsIgnoreCase(actualPwd))
			{
			System.out.println("Error message verified :" + expectedUsernm+" "+expectedPwd);
			}
		}
		}
    
   
		
		 public void wrongCredentials() throws InterruptedException 
		    {
				//String expected = "Invalid username or password";
				//waitForElementToAppear(wrongCredentialPopup);
			 Thread.sleep(4000);
				WebElement actual=wrongCredentialPopup; 
				
				Assert.assertEquals(actual.getText(),"Invalid username or password");
				/*if (actual.equalsIgnoreCase(actual)) 
				{
					
					System.out.println("Error message verified :" +expected);
				}*/
		    }
				
		public void successfulLogin() throws InterruptedException 
				  {
					 String expectedUrl ="https://stageweb.nibejpi.com/";
					 Thread.sleep(3000);
					 waitForElementToAppear(profileIcon);
		                String actualUrl=driver.getCurrentUrl();
		                Assert.assertEquals(actualUrl, expectedUrl);
		                
		                if(actualUrl.equalsIgnoreCase(expectedUrl))
		                {
		                	System.out.println("Logged in");
		                } 
	}
		
		public void selectLanguage()
		{
			WebElement languageButton=driver.findElement(By.xpath("//select[@class='languageSelector__selectControl']"));
			Select language=new Select(languageButton);
			language.selectByVisibleText("English");
			
		}
	
}
