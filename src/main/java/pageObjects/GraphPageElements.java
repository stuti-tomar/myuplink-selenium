package pageObjects;

public interface GraphPageElements {
	
	
	
	String ADD_PARAMETER="//span[normalize-space()='Add Parameter']";
	
	String PARAMETER_SELECTION_ICON =".chart-config-wrapper__topContent";
	
	String CALENDAR_BUTTON="//button[@class='quick-step-container__button']";
	
	String GRAPH_LINES="(//*[local-name()='svg']//*[name()='g'])[1]//*[name()='line']";
	
	String GRAPH_DATA_TOOLTIP="//div[@class='rv-crosshair__inner__content']";
	
	String YEAR="//span[normalize-space()='Year']";
	
	String YEAR_MONTH_NAMES="//*[local-name()='svg']//*[local-name()='g' and @class='rv-xy-plot__axis rv-xy-plot__axis--horizontal ']//*[local-name()='g' and @class='rv-xy-plot__axis__tick']";
	String YEAR_MONTH_LINE="(//*[local-name()='svg']//*[local-name()='g' and @class='rv-xy-plot__axis rv-xy-plot__axis--horizontal ']//*[local-name()='g' and @class='rv-xy-plot__axis__tick']//*[local-name()='line'])";
	String PREVIOUS_YEAR_ICON="//button[@aria-label='Previous date range']";
	
	String CHART_LIST_CONTAINER=".chart-list-container";
	
	String CLEAR_ALL_BTN=".clear-value-btn";
	
	//PARAMETERS
	String AVERAGE_TEMPERATURE="//span[normalize-space()='Average temper­ature (BT74)']";
	String CONTROLLING_ROOM_SENSOR="//span[normalize-space()='Controlling room sensor']";
	String BT1="//span[normalize-space()='Current outd temp (BT1)']";
	String ENERGY_CONSUMPTION_HEATING="//span[normalize-space()='Energy consumption heating [last day per hour]']";
	String SELECT="//span[normalize-space()='Select']";
	
	
	//Parameters addition
		String CONTENT_NIBE="//div[normalize-space()='NIBE S1255-6 E EM 3X400V']";
		String CONTENT_NIBE_PARAMTER_LIST=".parameter-title-container";
		
		
}
