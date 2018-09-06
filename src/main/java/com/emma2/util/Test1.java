package com.emma2.util;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Test1 extends TestBase {
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="suiteADataProvider")
	public void test1(Hashtable<String,String> table){

//		validateRunmodes("Test1", Constants.FIRST_SUITE, table.get("Runmode"));
	}
	
	/*@DataProvider
	public Object[][] getData(){
		
		ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\SuiteA.xlsx");
		return Utility.getData("Test1", excel);
		
	}*/

}
