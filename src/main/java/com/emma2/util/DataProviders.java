package com.emma2.util;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;


public class DataProviders extends TestBase{
	
	
	@DataProvider(name="suiteADataProvider")
	public static Object[][] getDataSuiteA(Method m){
//		init();
//		ExcelReader excel = new ExcelReader(prop.getProperty("xlsFileLocation")+"\\"+Constants.FIRST_SUITE+".xlsx");
//
//		return Utility.getData(m.getName(), excel);

		return new Object[0][];
	}
	
	@DataProvider(name="suiteBDataProvider")
	public static Object[][] getDataSuiteB(Method m){
//		init();
//		ExcelReader excel = new ExcelReader(prop.getProperty("xlsFileLocation")+"\\"+Constants.SECOND_SUITE+".xlsx");
//
//		return Utility.getData(m.getName(), excel);

		return new Object[0][];
	}
	
	@DataProvider(name="suiteCDataProvider")
	public static Object[][] getDataSuiteC(Method m){
//		init();
//		ExcelReader excel = new ExcelReader(prop.getProperty("xlsFileLocation")+"\\"+Constants.THIRD_SUITE+".xlsx");
//
//		return Utility.getData(m.getName(), excel);

		return new Object[0][];
	}
	
	
	

}
