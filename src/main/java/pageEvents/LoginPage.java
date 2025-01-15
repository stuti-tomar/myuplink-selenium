package pageEvents;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.LoginPageElements;
import utils.JSExecutorUtils;
import utils.OtpFetcher;
import utils.WaitUtils;

public class LoginPage extends WaitUtils implements LoginPageElements {
	WebDriver driver;
	HomePage homePage;
	OtpFetcher otpFetcher;
	WebDriverWait wait;
	JSExecutorUtils js; 

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		homePage = new HomePage(driver);
		js=new JSExecutorUtils(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		otpFetcher=new OtpFetcher();
	}

	@FindBy(xpath = ACCEPT_COOKIES_BUTTON)
	private WebElement acceptCookiesButton;

	@FindBy(xpath = FORGOT_PASSWORD_LINK)
	private WebElement ForgotPasswordButton;

	@FindBy(xpath = EMAIL_FIELD)
	private WebElement emailField;

	@FindBy(xpath = PASSWORD_FIELD)
	private WebElement passwordField;

	@FindBy(id = WRONG_EMAIL_MESSAGE)
	private WebElement wrongEmailFormatMessage;

	@FindBy(xpath = LOGIN_BUTTON)
	private WebElement loginButton;

	@FindBy(xpath = WRONG_CREDENTIALS_MESSAGE)
	private WebElement wrongCredentialsMessage;

	@FindBy(xpath = CONTINUE_WITH_EMAIL_BUTTON)
	WebElement continueWithEmailButton;

	@FindBy(xpath = INCORRECT_FORMAT_EMAIL_MESSAGE)
	WebElement incorrectFormatEmailMessage;

	@FindBy(xpath = BLANK_EMAIL_MESSAGE)
	WebElement blankEmailMessage;

	@FindBy(xpath = BLANK_PASSWORD_MESSAGE)
	WebElement blankPasswordMessage;
	
	@FindBy(xpath=SEND_CODE)
	WebElement sendCode;
	
	@FindBy(id=VERIFICATION_CODE_FIELD)
	WebElement verificationCodeField;
	
	@FindBy(xpath=VERIFICATION_CODE_SUBMIT_BUTTON)
	WebElement verificationCodeSubmitButton;
	
	@FindBy(id=CONTINUE_BUTTON)
	WebElement continueButton;

	public void acceptCookies() {
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
	        
	        js.scrollToElement(acceptCookiesButton);
	        
	        acceptCookiesButton.click();
	    } catch (Exception e) {
	    	waitForVisibilityOfElement(acceptCookiesButton);
	        // If the direct click fails, retry with the retry logic
	        System.out.println("Direct click failed. Retrying...");
	        js.clickWithRetry(acceptCookiesButton);
	    }
	}

	public void enterUsername(String username) {
		emailField.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickLoginButton() {
		loginButton.click();
	}

	public void clickContinueWithEmailButton() {
		continueWithEmailButton.click();
	}
	
	public void clickSendCode() {
		waitForVisibilityOfElement(sendCode);
		sendCode.click();
	} 
	
	public void enterEmailVerificationCode()
	{
		waitForSeconds(10);
		String otp=otpFetcher.fetchOtpFromEmail();
		verificationCodeField.sendKeys(otp);
		verificationCodeSubmitButton.click();
		waitForVisibilityOfElement(continueButton);
		continueButton.click();
	}

	public void clickForgotPasswordButton() {
		ForgotPasswordButton.click();
	}

	public void verifyIncorrectFormatEmailAndCorrectPassword() {
		String expected = "Username should be an e-mail address";
		String actual = incorrectFormatEmailMessage.getText();
		Assert.assertEquals(expected, actual);
	}

	public void verifyBlankEmailOrBlankPassword() {
		String expectedEmailMessage = "Please enter your E-mail";
		String expectedPasswordMessage = "Please enter your password";

		String actualEmailMessage = blankEmailMessage.getText();
		String actualPasswordMessage = blankPasswordMessage.getText();

		// Scenario 1: Blank Email Only
		if (!actualEmailMessage.isEmpty() && actualPasswordMessage.isEmpty()) {
			Assert.assertEquals(actualEmailMessage, expectedEmailMessage, "Email error message mismatch!");
		}

		// Scenario 2: Blank Password Only
		else if (!actualPasswordMessage.isEmpty() && actualEmailMessage.isEmpty()) {
			Assert.assertEquals(actualPasswordMessage, expectedPasswordMessage, "Password error message mismatch!");
		}

		// Scenario 3: Both Blank
		else if (!actualEmailMessage.isEmpty() && !actualPasswordMessage.isEmpty()) {
			Assert.assertEquals(actualEmailMessage, expectedEmailMessage, "Email error message mismatch!");
			Assert.assertEquals(actualPasswordMessage, expectedPasswordMessage, "Password error message mismatch!");
		}
	}

	public void verifyWrongCredentials() throws InterruptedException {
		waitForVisibilityOfElement(wrongCredentialsMessage);

		Assert.assertEquals(wrongCredentialsMessage.getText(), "Invalid username or password.");
	}

	public void verifySuccessfulLogin() throws InterruptedException {
		homePage.verifyVisibilityOfProfileIcon();
	}

}
