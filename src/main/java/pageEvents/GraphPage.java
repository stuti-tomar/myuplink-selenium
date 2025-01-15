package pageEvents;

import java.util.List;

import org.myuplink.abstractmethods.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pageObjects.GraphPageElements;

public class GraphPage extends AbstractComponent implements GraphPageElements 
{
	WebDriver driver;

	public GraphPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ADD_PARAMETER)
	WebElement addParameter;
	
	
	@FindBy(css=PARAMETER_SELECTION_ICON)
	WebElement parameterSelectionIcon;
	
	@FindBy(xpath=AVERAGE_TEMPERATURE)
	WebElement averageTemperature;
	
	@FindBy(xpath=CONTROLLING_ROOM_SENSOR)
	WebElement controllingRoomSensor;
	
	@FindBy(xpath=BT1)
	WebElement bt1;
	
	@FindBy(xpath=ENERGY_CONSUMPTION_HEATING)
	WebElement energyConsumptionHeating;
	
	@FindBy(xpath=SELECT)
	WebElement select;
	
	@FindBy(xpath=CALENDAR_BUTTON)
	WebElement calendarBtn;
	
	@FindBy(xpath=GRAPH_LINES)
	List <WebElement> graphLines;
	
	@FindBy(xpath=GRAPH_DATA_TOOLTIP)
	WebElement graphDataTooltip;
	
	@FindBy(xpath = CONTENT_NIBE)
	WebElement contentNIBE;
	
	
	@FindBy(xpath=YEAR_MONTH_NAMES)
	List<WebElement> nameOfMonth;
	
	@FindBy(xpath = YEAR)
	WebElement year;
	
	
	@FindBy(xpath=YEAR_MONTH_LINE)
	List<WebElement> monthLine;
	
	@FindBy(xpath=PREVIOUS_YEAR_ICON)
	WebElement previousMonthIcon;
	
	@FindBy(css=CONTENT_NIBE_PARAMTER_LIST)
	List <WebElement> content_NIBE_parameterList; 
	
	
	@FindBy(css=CHART_LIST_CONTAINER)
	List <WebElement> chartListContainer; 
	
	
	@FindBy(css=CLEAR_ALL_BTN)
	WebElement clearAll; 

	public void addParameter() throws InterruptedException 
	{
		addParameter.click();
		contentNIBE.click();
		averageTemperature.click();
		scroll(select);
		select.click();
	}
	
	public void reachToYearGraphPage() throws InterruptedException
	{ 
		year.click();
		previousMonthIcon.click();
		previousMonthIcon.click();
		
	}
	
	
	public void addMaxParameters() throws InterruptedException
	{
		/*addParameter.click();		
		contentNIBE.click();
		
		
		for (int i=0;i<10;i++)
		{
			//System.out.println("Before click" +content_NIBE_parameterList.get(i).isSelected());
			content_NIBE_parameterList.get(i).click();
			//System.out.println(list.get(i).getText());
			//System.out.println("After click" +content_NIBE_parameterList.get(i).isSelected());
			Thread.sleep(1000);
			Boolean checkboxStatus=content_NIBE_parameterList.get(i).isSelected();
			Assert.assertFalse(checkboxStatus);
		}*/
		
		 addParameter.click();
	        contentNIBE.click();

	        content_NIBE_parameterList.stream() .limit(10) // Limit the stream to the first 10 elements
	                .forEach(parameter -> {
	                    parameter.click();
	                    boolean checkboxStatus = parameter.isSelected();
	                    Assert.assertFalse(checkboxStatus);
	                });
	    
	}
		
		public void addMoreThanTenParamters() throws InterruptedException
		{
			content_NIBE_parameterList.get(10).click();
			//String h=content_NIBE_parameterList.get(10).getText();
			//System.out.println(h);
			//WebElement g=driver.findElement(By.xpath("//span[normalize-space()='Degree minutes']"));
			
			Thread.sleep(2000);
			Boolean checkboxStatus=content_NIBE_parameterList.get(10).isSelected();
			Assert.assertFalse(checkboxStatus);
		}
		
		
		public void verifyDeleteAllParameters() 
		{
			
		}
			
			
			
			
		
		
	
	
	
	
	public void verifyYearGraphData(String monthName1, String monthName2, String monthName3) throws InterruptedException
	{
		Actions act=new Actions(driver);
		/*for(WebElement name:nameOfMonth)
		{
			String s=name.getText();
			
			if(s.equals(monthName1) || s.equals(monthName2) || s.equals(monthName3))
			{
				act.moveToElement(name).perform();
				String graphData=graphDataTooltip.getText();
				System.out.println(graphData);
				Thread.sleep(2000);
			}
			
		}*/
		
		
		nameOfMonth.stream().filter(name -> 
        {
            String s = name.getText();
            return s.equals(monthName1) || s.equals(monthName2) || s.equals(monthName3);
        })
        .forEach(name -> {
            act.moveToElement(name).perform();
            String graphData = graphDataTooltip.getText();
            System.out.println(graphData);
        });
	}
		 
		
		
	}
	


	

		
		
	
	
	



 
			
		

			


