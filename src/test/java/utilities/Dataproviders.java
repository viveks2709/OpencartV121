package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders {
	
	//Data provider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";//Taking excel file from test data
		ExcelUtility xlutil=new ExcelUtility(path);// Creating an Object of XLUtility
		
		int totalrows= xlutil.getRowcount("Sheet1");
		int totalcols=xlutil.getCellcount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcols];//Created for two dimensional array which can store 
				
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//1,0
			}
		}
		
	return logindata;	
		
		
		
	}

}
