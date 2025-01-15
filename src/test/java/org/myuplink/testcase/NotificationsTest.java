package org.myuplink.testcase;


import org.myuplink.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageEvents.LoginPage;
import pageEvents.NotificationsPage;
import utils.Constant;
import utils.ExcelUtils;
    
    public class NotificationsTest extends BaseTest
    {
    	NotificationsPage notifications=new NotificationsPage(driver);
    	LoginPage loginPage=new LoginPage(driver);
    	
    	@BeforeMethod(description = "Reaching to History page to access the graph")
    	public void reachToCalendarPage() throws Exception {
    		ExcelUtils.setExcelFile(Constant.CONS_TEST_DATA, "Login");
    		loginPage = new LoginPage(driver);
    		notifications=new NotificationsPage(driver);
    		loginPage.acceptCookies();
    		loginPage.enterUsername(ExcelUtils.getCellData(4, 1));
    		loginPage.enterPassword(ExcelUtils.getCellData(4, 2));
    		loginPage.clickLoginButton();
    		loginPage.verifySuccessfulLogin();  
    		notifications.reachNotificationTab();    		
    	}
    	
            @Test(description="disable all the present notifications")     
            public void testDisableNotifications() throws InterruptedException 
            {
            	notifications.disableNotifications();     		            	
                
            }
            
            @Test
            public void testSelectingNotifications()
            {
            	notifications.optNotifications();
            }

            
               
    }
            
        
    
   
   
