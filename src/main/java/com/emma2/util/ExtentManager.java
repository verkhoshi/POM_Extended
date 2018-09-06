//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html

package com.emma2.util;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import ru.yandex.qatools.properties.annotations.Resource;

public class ExtentManager {
	private static ExtentReports extent;
	
	private ExtentManager(){}

	public static ExtentReports getInstance() {
		if (extent == null) {
			Date d= new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports(System.getProperty("user.dir")+File.separator+fileName, true, DisplayOrder.OLDEST_FIRST);

	
//			extent.loadConfig(new File(System.getProperty("user.dir") + "//src//test//resources//extentconfig//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "2.53.1").addSystemInfo(
					"Environment", "PROD");
		}
		return extent;
	}
}
