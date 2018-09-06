package com.emma2.util;


import java.util.Calendar;
import java.util.Formatter;


public class DateUtility 
{

	public String rollupDates(String rollup) throws Exception
	{
		try {
			Formatter format = new Formatter();
			Calendar cal = Calendar.getInstance();
			if (rollup.equalsIgnoreCase("month")) {
				cal.add(cal.MONTH, -4);
			}
			format = new Formatter();
			return format.format("%tb", cal).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
