package pageObjects;

public interface LoginPageElements {
	
	String ACCEPT_COOKIES_BUTTON="onetrust-accept-btn-handler";
	String EMAIL="//input[@type='email']";
	String PASSWORD="//input[@type='password']";
	String WRONG_EMAIL_MSG="inputComponent__error";
	String LOGIN_BTN="//button[@type='submit']";
	String FORGOT_PASSWORD_LINK = "//a[@class='form__link'] ";
	String CONTINUE_WITH_EMAIL_BUTTON="//span[normalize-space()='Continue with email']";
	
	
	
	//error messages
	
	String WRONG_CRED_MSG="//div[@class='error pageLevel']";
	
	
	
	
	
	

}
