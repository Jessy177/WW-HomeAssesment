package com.uiseleniumfw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.Assert;

public class Report {
	
	String strPath,strTestName,strImagePath;
	int intStepNo=0;
	boolean isTestFailed=false;
	public Report(String _strPath,String _strTestName)
	{
		strPath=_strPath;
		strTestName=_strTestName;
		
		
	}
	
	
	public void CreateHeader()
	{
		try {
		      File myObj = new File(strPath+"/"+strTestName+".html");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } 
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		WritetoFile("<html>\n"
				+ "<head>\n"
				+ "<style>\n"
				+ "table, th, td {\n"
				+ "  border: 1px solid black;\n"
				+ "  border-collapse: collapse;\n"
				+ "}\n"
				+ "</style>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "<table style=\"width:100%\">\n"
				+ "  <tr>\n"
				+ "    <th>Sno</th>\n"
				+ "    <th>Function Name</th>\n"
				+ "    <th>Object Name</th> \n"
				+ "    <th>Expected</th>\n"
				+ "    <th>Actual</th>\n"
				+ "    <th>Status</th>\n"
				+ "  </tr>");
		
		
	}
	
	
	public void WritetoFile(String strContent)
	{
		try {
		      FileWriter myWriter = new FileWriter(strPath+"/"+strTestName+".html");
		      myWriter.write(strContent);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void FinalizeReport()
	{
		WritetoFile("</table>\n"
				+ "\n"
				+ "</body>\n"
				+ "</html>");
		if(isTestFailed)
		{
		Assert.fail("Please Check the Execution Report : "+strPath+"/"+strTestName+".html");
		}
	}
	
public void InsertPomName(String strPomName)
{
	intStepNo++;
	WritetoFile("<tr>\n"
			+ "    <th>"+strPomName+"</th>"
			);

}
	public void GenerateReport(String strFunctionName,String strObjName,String strExpected,String strActual,String strStatus)
	{
		intStepNo++;
		if(strStatus.toUpperCase()=="Fail")
		{
			isTestFailed=true;
		}
		WritetoFile("<tr>\n"
				+ "    <th>"+intStepNo+"</th>\n"
				+ "    <th></th>\n"
				+ "    <th>"+strFunctionName+"</th>\n"
				+ "    <th>"+strObjName+"</th> \n"
				+ "    <th>"+strExpected+"</th>\n"
				+ "    <th>"+strActual+"</th>\n"
				+ "    <th>"+strStatus+"</th>\n"
				+ "  </tr>");
	}
	
}
