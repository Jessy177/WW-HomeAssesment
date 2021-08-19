package com.wwassessment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.uiseleniumfw.FrameWork;

public class StudioDetails {
	FrameWork fw;
	
	public StudioDetails(FrameWork _fw) {
		this.fw = _fw;

		
	}
	
	public String getTitle() {
		
		By wbeTitle = By.className("locationName-1jro_");
		WebElement wbeTitleObject =fw.getElement(wbeTitle, 0);
		String strTitle ="";
		if(wbeTitleObject != null) {
			strTitle=wbeTitleObject.getText();
		}
		
		return strTitle;
		
	}
	
	public void  ExpandBusinessHours() {
		
		By byBusinessHours = By.className("title-3o8Pv");
		fw.wbeClickWebElement(byBusinessHours, 0);
		
		
	}
	
	public void GetBusinessHours() {
		
		By byBusinessHoursContr = By.className("hours-IVyrp");
		By byDailyBusiHours = By.className("day-CZkDC");
		List<WebElement> lstBusiHours =fw.getElementsWithinParentControl(fw.getElement(byBusinessHoursContr, 0),byDailyBusiHours);
		for(WebElement obj: lstBusiHours) {
			System.out.println(obj.getText());
		}
		
		
		
	}
	
}
