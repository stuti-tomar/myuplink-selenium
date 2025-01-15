package extentreport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject()
	{		
		String path=System.getProperty("user.dir") + "//reports//UplinkExtentReport.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
		sparkReporter.config().setReportName("Test Reports");
		sparkReporter.config().setDocumentTitle("Reports");
		sparkReporter.config().setTheme(Theme.DARK);
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("UserName", "Stuti");
		return extent;
	}

}
