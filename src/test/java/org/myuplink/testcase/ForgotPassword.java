package org.myuplink.testcase;
import org.myuplink.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import pageEvents.LoginPage;
import pageEvents.ResetPasswordPage;
import utils.Constant;
import utils.ExcelUtils;


public class ForgotPassword extends BaseTest{
	private LoginPage loginPage;
	private ResetPasswordPage resetPass;
	
	
	@BeforeMethod
	public void setUp() throws Exception
	{
		ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "ForgotPassword");
         loginPage = new LoginPage(driver);
         resetPass = new ResetPasswordPage(driver);
         loginPage.acceptCookies();
	}
    
    @Test(priority=1)
    public void testBlankEmail() throws Exception 
    {
        
        loginPage.clickForgotPwdBtn();
        
        
        resetPass.clickResetPasswordButton();
        resetPass.enterResetEmail(ExcelUtils.getCellData(1, 1));
        Thread.sleep(3000);
        resetPass.verifyEmptyResetEmail();
    }
    
    @Test(priority=2)
    public void testWrongFormatEmail() throws Exception 
    {

        loginPage.clickForgotPwdBtn();
        Thread.sleep(2000);
        resetPass.enterResetEmail(ExcelUtils.getCellData(2, 1));
        resetPass.clickResetPasswordButton();
        Thread.sleep(2000);
        resetPass.verifyWrongFormatResetEmail();
    }
    
    
    @Test(priority=3)
    public void testCorrectResetEmail() throws Exception 
    {
        
         loginPage.clickForgotPwdBtn();
         resetPass.enterResetEmail(ExcelUtils.getCellData(3, 1));
        resetPass.clickResetPasswordButton();
        resetPass.verifyMsgWithCorrectResetEmail();
    }
    
}	




    
  

   
    
    
   

    


