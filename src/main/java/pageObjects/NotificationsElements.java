package pageObjects;
public interface NotificationsElements {
	
	String GENERAL_MENU="//span[normalize-space()='General']";

   String NOTIFICATION_MENU="//a[@class='desktopSubMenu__link']//span[contains(text(),'Notifications')]";
   
   String NOTIFICATION_INBOX="//li[@class='tabBlock-tab is-active']";
   
   String NO_NOTIFICATION_INFO="//span[normalize-space()='There are no notifications.']";
   
   String PROFILE_ICON="menu.headers.profile";
   
   String PROFILE_SETTINGS="(//span[normalize-space()='Profile Settings'])[1]";
   
   String NOTIFICATION_TAB="(//span[normalize-space()='Notifications'])[2]";
   
   String TOGGLE_TEXT_OFF="(//div[normalize-space()='Off'])[3]";
   
   String TOGGLE_TEXT_ON="(//div[normalize-space()='On'])[3]";
   
   String TOGGLE_BUTTON="//label[@for='toggle-switch-undefined']";
   
   
 

}
