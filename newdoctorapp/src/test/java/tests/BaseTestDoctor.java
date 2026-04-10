package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class BaseTestDoctor {
		protected static ExtentReports extent;
		
		@BeforeSuite
		public void setupReport() {
			ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/extent-report.html");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		
		@AfterSuite
		public void tearDownReport() {
			if(extent != null) {
				extent.flush();
			}
		}
}

