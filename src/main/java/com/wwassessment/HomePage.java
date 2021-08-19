package com.wwassessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiseleniumfw.FrameWork;
import com.uiseleniumfw.LocatorType;

public class HomePage {
	FrameWork fw;
	
	
	
	public HomePage(FrameWork _fw) {
		this.fw = _fw;

		
	}
	
	public void FindYourWorkShop(WorkShopType wst, String strSearchString) {
		 
		
		By wbeWorkShopType = By.className("toggleButton-2ikVW"); 
		By wbeLocation=By.id("location-search");
		By btnLocSearch=By.className("rightArrow-daPRP");
		
		switch(wst) {
		case VIRTUAL:
			
			fw.wbeClickWebElement(wbeWorkShopType, 0);
			break;
		case STUDIO:
			fw.wbeClickWebElement(wbeWorkShopType, 1);
			fw.EnterWebEdit(wbeLocation, 0, strSearchString);
			fw.wbeClickWebElement(btnLocSearch, 0);
			break;
		}
		
	}
	
	public WebElement getWorkShopLocation(int intLocationIndex) {
		 
		By wbeLocationParent = By.className("container-3SE46");
		WebElement wbeObject = fw.getElement(wbeLocationParent, intLocationIndex);
		
		return wbeObject;
		
	}
	
	
	public String getLocationTitle(int intLocationIndex) {
		 
		By wbeTitle = By.className("linkContainer-1NkqM");
		WebElement wbeTitleObject =fw.getElementWithinParentControl(getWorkShopLocation(intLocationIndex), wbeTitle, 0);
		String strTitle ="";
		if(wbeTitleObject != null) {
			strTitle=wbeTitleObject.getText();
		}
		
		return strTitle;
		
	}
	
	public String getLocationMiles(int intLocationIndex) {
		 
		By wbeDistance = By.className("distance-OhP63");
		WebElement wbeDistanceObject =fw.getElementWithinParentControl(getWorkShopLocation(intLocationIndex), wbeDistance, 0);
		String strMiles ="";
		if(wbeDistanceObject != null) {
			strMiles=wbeDistanceObject.getText();
		}
		
		return strMiles;
		
	}
	
	public void selectLocation(String strTitle) {
		 
		fw.clickWebLinkbyLinkText(strTitle, 0);
	}
	
	
	
	
	

}
