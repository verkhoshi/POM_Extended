package com.emma2.util;

import java.util.Hashtable;

public class Utility {
	
	
	public static boolean isSuiteRunnable(String suiteName, ExcelReader excel){
		
//		int rows = excel.getRowCount(Constants.SUITE_SHEET);
//
//		for(int rNum=2; rNum<=rows; rNum++){
//
//			String data = excel.getCellData(Constants.SUITE_SHEET, Constants.SUITENAME_COL, rNum);
//
//			if(data.equals(suiteName)){
//
//				String runmode=excel.getCellData(Constants.SUITE_SHEET, Constants.RUNMODE_COL, rNum);
//				if(runmode.equals(Constants.RUNMODE_YES))
//					return true;
//				else
//					return false;
//			}
//
//
//			//System.out.println(data);
//		}
		return false;
		
		
	}

	
	
	public static boolean isTestCaseRunnable(String testCase, ExcelReader excel){
		
		
//		int rows = excel.getRowCount(Constants.TESTCASES_SHEET);
//
//		for(int rNum=2; rNum<=rows; rNum++){
//
//			String testName = excel.getCellData(Constants.TESTCASES_SHEET, Constants.TESTCASES_COL, rNum);
//
//			if(testName.equals(testCase)){
//
//				String runmode=excel.getCellData(Constants.TESTCASES_SHEET, Constants.RUNMODE_COL, rNum);
//				if(runmode.equals(Constants.RUNMODE_YES))
//					return true;
//				else
//					return false;
//			}
//
//
//			//System.out.println(data);
//		}
//
		
		return false;
	}
	
	
	
	
//	public static Object[][] getData(String testName, ExcelReader excel){
//
//
//		int rows = excel.getRowCount(Constants.DATA_SHEET);
//		System.out.println("Total rows are : "+rows);
//
//
//		//row number for test case
//		int testCaseRowNum=1;
//		for(testCaseRowNum=1;testCaseRowNum<=rows;testCaseRowNum++){
//
//			String testNameXls = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
//			if(testNameXls.equalsIgnoreCase(testName))
//				break;
//
//
//		}
//
//		System.out.println("Test starts from row num - "+testCaseRowNum);
//
//		int dataStartRowNum = testCaseRowNum+2;
//		int colStartColNum = testCaseRowNum+1;
//
//
//		//rows in a test
//		int testRows = 0;
//		while(!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).equals("")){
//
//			testRows++;
//
//		}
//
//		System.out.println("Total rows of data are :  "+testRows);
//
//		//Cols of Data in a test
//
//		int testCols=0;
//		while(!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).equals("")){
//
//			testCols++;
//		}
//
//		System.out.println("Total cols  "+testCols);
//
//		//Print data
//
//		Object[][] data = new Object[testRows][1];
//		int r=0;
//		for(int rNum=dataStartRowNum; rNum<(dataStartRowNum+testRows);rNum++){
//			Hashtable<String,String> table = new Hashtable<String,String>();
//
//			for(int cNum=0; cNum<testCols; cNum++){
//
//				//System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
//			//data[r][cNum] = excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
//			table.put(excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum), excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
//			}
//			data[r][0] = table;
//			r++;
//		}
//
//		//0,0 0,1
//		//1,0 1,1
//		return data;
//	}
	
	
	
}
