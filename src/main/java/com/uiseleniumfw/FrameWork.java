package com.uiseleniumfw;
import org.testng.Assert;			

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;

public class FrameWork {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public List<WebElement> wbeParentCollection = new ArrayList<WebElement>();
	
	public WebDriver driver;
	
	boolean blTestStepFailed=false;
	Report report;
		
	public void InitTest(String strbaseUrl)
	{
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.prompt_for_download", "false");
		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("prefs", chromePrefs);

		// options.addArguments("--start-maximized");
		options.addArguments("window-size=1280,1024");

		options.addArguments("--ignore-certificate-errors");
		DesiredCapabilities capablity = DesiredCapabilities.chrome();

		options.addArguments("--incognito");
		capablity.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		capablity.setCapability(ChromeOptions.CAPABILITY, options);
		capablity.setBrowserName("chrome");
		System.setProperty("webdriver.chrome.driver", "/Users/Jessy/SeleniumDrivers/chromedriver");
		driver = new ChromeDriver(options);
		//strTestcaseName=strTestcaseName+"_"+UUID.randomUUID().toString();
		driver.get(strbaseUrl);
		driver.manage().window().maximize();
	//	report=new Report("/Users/jessy/SeleniumDrivers/Reports/",strTestcaseName);
		
		//report.CreateHeader();
		
	}
	
	public void EndTest()
	{
		driver.quit();
		if(blTestStepFailed)
		{
			Assert.fail();
		}
		//report.FinalizeReport();
	}
	
	
	public void validateTextInPage(String strText,Boolean blShouldPresent)
	{
		Boolean isPageTextPresent=false;
		try {
			isPageTextPresent=driver.getPageSource().contains(strText);
			
		}
		catch(Exception ex)
		{
			LOGGER.log(Level.WARNING,ex.getMessage());
		}
		if(blShouldPresent)
		{
			
			if(isPageTextPresent)
			{
				LOGGER.log(Level.INFO,strText+" : is Present in the webpage as Expected");
			
				
			}
			else
			{
				LOGGER.log(Level.SEVERE,strText+" : is not Present in the webpage");
				blTestStepFailed=true;
 			}
		}
		else
		{
			if(isPageTextPresent)
			{
				LOGGER.log(Level.SEVERE,strText+" : is Present in the webpage");
				blTestStepFailed=true;
			}
			else
			{
				LOGGER.log(Level.INFO,strText+" : is not Present in the webpage as Expected");
			}
		}
		
	}
	
	public List<WebElement> getElements(By byLocator){
		List<WebElement> lstwbeObjects = new ArrayList<WebElement>();
		wbeParentCollection.clear();
		try {
		
				lstwbeObjects=driver.findElements(byLocator);
			
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING,ex.getMessage());
		}
		
		return lstwbeObjects;
	}
	
	public List<WebElement> getElementsWithinParentControl(WebElement wbeParentControl,By byLocator){
		List<WebElement> lstwbeObjects = new ArrayList<WebElement>();
		wbeParentCollection.clear();
		try {
		
				lstwbeObjects=wbeParentControl.findElements(byLocator);
			
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING,ex.getMessage());
		}
		
		return lstwbeObjects;
	}
	
	public WebElement getElement(By byLocator, int intObjIndex) {
		WebElement wbeObj=null;
		try {
			
			wbeParentCollection = getElements(byLocator);
			if(wbeParentCollection.size()-1>= intObjIndex)
			{
				wbeObj= wbeParentCollection.get(intObjIndex);
				LOGGER.log(Level.INFO,"Successfully Clicked "+byLocator.toString()+" :Object of Index :"+ intObjIndex);
				
			}else {
				LOGGER.log(Level.SEVERE,byLocator.toString()+" :Object of Index :"+ intObjIndex+" :not found");
				blTestStepFailed=true;
			}
			
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING,ex.getMessage());
		}
		return wbeObj;
		
	}
	
	public WebElement getElementWithinParentControl(WebElement wbeParentControl, By byLocator, int intObjIndex) {
		WebElement wbeObj=null;
		try {
			
			wbeParentCollection = getElementsWithinParentControl(wbeParentControl,byLocator);
			if(wbeParentCollection.size()-1>= intObjIndex)
			{
				wbeObj= wbeParentCollection.get(intObjIndex);
				LOGGER.log(Level.INFO,"Successfully Clicked "+byLocator.toString()+" :Object of Index :"+ intObjIndex);
				
			}else {
				LOGGER.log(Level.SEVERE,byLocator.toString()+" :Object of Index :"+ intObjIndex+" :not found");
				blTestStepFailed=true;
			}
			
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING,ex.getMessage());
		}
		return wbeObj;
		
	}
	
	public void wbeClickWebElement(By byLocator,int intObjIndex) {
		try {
			wbeParentCollection = getElements(byLocator);
			if(wbeParentCollection.size()-1>= intObjIndex)
			{
				wbeParentCollection.get(intObjIndex).click();
				LOGGER.log(Level.INFO,"Successfully Clicked "+byLocator.toString()+" :Object of Index :"+ intObjIndex);
				
			}else {
				LOGGER.log(Level.SEVERE,byLocator.toString()+" :Object of Index :"+ intObjIndex+" :not found");
				blTestStepFailed=true;
			}
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING,ex.getMessage());
			
		}
	}
	
	public void EnterWebEdit(By byLocator,int intObjIndex, String strInput) {
		try {
			wbeParentCollection = getElements(byLocator);
			if(wbeParentCollection.size()-1>= intObjIndex)
			{
				if(wbeParentCollection.get(intObjIndex).isEnabled()) {
					wbeParentCollection.get(intObjIndex).sendKeys(strInput);
					LOGGER.log(Level.INFO,"Successfully Entered "+byLocator.toString()+" :Object of Index :"+ intObjIndex);
				}
				else {
					LOGGER.log(Level.SEVERE,byLocator.toString()+" :Webedit of Index :"+ intObjIndex+" :is in disabled state");
					blTestStepFailed=true;
				}
				
				
				
			}else {
				LOGGER.log(Level.SEVERE,byLocator.toString()+" :Object of Index :"+ intObjIndex+" :not found");
				blTestStepFailed=true;
			}
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING,ex.getMessage());
			
		}
	}
	public String getTextfromWebElement(WebElement wbeObject) {
		String strContent ="";
		
		if(wbeObject != null) {
		strContent = wbeObject.getText();
		}
			
		return strContent;
		
		
	}
	
	public void clickWebLinkbyLinkText(String strLinkText, int intObjIndex) {
		
		By byWebLink =By.linkText(strLinkText);
		try {
			wbeParentCollection = getElements(byWebLink);
			if(wbeParentCollection.size()-1>= intObjIndex)
			{
				wbeParentCollection.get(intObjIndex).click();
				LOGGER.log(Level.INFO,"Successfully Clicked "+byWebLink.toString()+" :Object of Index :"+ intObjIndex);
				
			}else {
				LOGGER.log(Level.SEVERE,byWebLink.toString()+" :Object of Index :"+ intObjIndex+" :not found");
				blTestStepFailed=true;
			}
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING,ex.getMessage());
			
		}
		
	}
	
	public void VerifyText(String strFirstString, String strSecondString, String strValidation) {
		if(strFirstString.toUpperCase().trim()==strSecondString.toUpperCase().trim()) {
			if(strFirstString.trim()==strSecondString.trim()) {
				LOGGER.log(Level.INFO,strValidation+" :"+strFirstString+" : "+strSecondString+" is Matching");

			}
			else {
				LOGGER.log(Level.WARNING,strValidation+" :"+strFirstString+" : "+strSecondString+" Case is Not Matching");

			}
			
		}
		else {
			LOGGER.log(Level.SEVERE,strValidation+" :"+strFirstString+" : "+strSecondString+" is Not Matching");
			blTestStepFailed=true;
			
		}
		
	}

}
