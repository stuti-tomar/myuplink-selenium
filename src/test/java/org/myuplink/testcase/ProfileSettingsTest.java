package org.myuplink.testcase;
import org.myuplink.base.BaseTest;
import org.testng.annotations.Test;


import pageEvents.LoginPage;
import pageEvents.HomePage;
import pageEvents.ProfileSettingPage;
    
    public class ProfileSettingsTest extends BaseTest{
	
        @Test()
        
        public void settingProfile() throws Exception { 
        	
        		LoginPage loginPage=new LoginPage(driver);
           	 loginPage.acceptCookies();
           	 Thread.sleep(2000);
               loginPage.enterUsername("rishikesh.mishra@nibejpi.com");
               loginPage.enterPassword("12345678Ab");
               loginPage.clickLoginButton();
               Thread.sleep(4000);
        		HomePage page=new HomePage(driver);
        		ProfileSettingPage ps=new ProfileSettingPage(driver);
        		
        		page.clickProfileBtn();
        		ps.clickProfileSettingButton();
        		ps.scrollToProfileSetting();
        	
           
            }
    }
            
        
    
   
   
