package pageObjects;

public interface LoginPageElements {
	
	String ACCEPT_COOKIES_BUTTON="//button[contains(text(),'Accept All')]";
	String EMAIL_FIELD="//input[@type='email']";
	String PASSWORD_FIELD="//input[@type='password']";
	String WRONG_EMAIL_MESSAGE="inputComponent__error";
	String LOGIN_BUTTON="//button[@type='submit']";
	String FORGOT_PASSWORD_LINK = "//a[@class='form__link'] ";
	String CONTINUE_WITH_EMAIL_BUTTON="//span[normalize-space()='Continue with email']";	
	String WRONG_CREDENTIALS_MESSAGE="//div[@class='error pageLevel']";
	String INCORRECT_FORMAT_EMAIL_MESSAGE="//p[normalize-space()='Username should be an e-mail address']";
	String BLANK_EMAIL_MESSAGE="//p[normalize-space()='Please enter your E-mail']";
	String BLANK_PASSWORD_MESSAGE="//p[normalize-space()='Please enter your password']";
	String SEND_CODE="//button[@class='sendCode']";
	String VERIFICATION_CODE_FIELD="VerificationCode";
	String VERIFICATION_CODE_SUBMIT_BUTTON="//button[@class='verifyCode']";
	String CONTINUE_BUTTON="continue";
	
	
	
	
	
	

}
