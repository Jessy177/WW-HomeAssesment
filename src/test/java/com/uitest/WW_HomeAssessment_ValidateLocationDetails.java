package com.uitest;

import org.testng.annotations.Test;

import com.uiseleniumfw.FrameWork;
import com.wwassessment.*;
import com.wwassessment.WorkShopType;

public class WW_HomeAssessment_ValidateLocationDetails {
  @Test
  public void f() {
	  FrameWork fw = new FrameWork();
	  try {
	  fw.InitTest("https://www.weightwatchers.com/us/find-a-workshop/");
	  fw.validateTextInPage("Find WW Studios & Meetings Near You | WW USA",true);
	  HomePage pomhomepage = new HomePage(fw);
	  pomhomepage.FindYourWorkShop(WorkShopType.STUDIO, "10011");
	  String strLocTitle = pomhomepage.getLocationTitle(0);
	  System.out.println(strLocTitle);
	  String strDistance = pomhomepage.getLocationMiles(0);
	  System.out.println(strDistance);
	  pomhomepage.selectLocation(strLocTitle);
	  StudioDetails pomStudiodetails = new StudioDetails(fw);
	  String strTitle = pomStudiodetails.getTitle();
	  fw.VerifyText(strLocTitle, strTitle, "Validating Location Title from search page and Location details Page");
	  pomStudiodetails.ExpandBusinessHours();
	  pomStudiodetails.GetBusinessHours();
	  }
	  catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally {
	  fw.EndTest();
	  }
  }
}
